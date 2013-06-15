package controllers

import play.api.mvc.{Action, Controller}
import org.jsoup.Jsoup
import com.github.sonic.parser.ArticleParser
import play.api.libs.json.Json
import play.api.data.Form
import play.api.data.Forms._
import com.ning.http.client.{AsyncHttpClientConfig, AsyncHttpClient}

/**
 * The Class Home.
 *
 * @author Nguyen Duc Dung
 * @since 6/12/13 10:39 PM
 *
 */
object Home extends Controller {

  lazy val config = new AsyncHttpClientConfig.Builder()
    .setCompressionEnabled(true)
    .setFollowRedirects(true)
    .build()

  def index = Action {
    Ok(views.html.home())
  }

  def parseUrl = Action {
    implicit request =>
      Form("url" -> nonEmptyText).bindFromRequest.fold(
        error => NotFound,
        url => {
          val httpClient = new AsyncHttpClient(config)
          var downloadTime = System.currentTimeMillis()
          val response = httpClient.prepareGet(url).execute().get()
          downloadTime = System.currentTimeMillis() - downloadTime
          var parseTime = System.currentTimeMillis()
          val doc = Jsoup.parse(response.getResponseBodyAsStream, null, url)
          val parser = new ArticleParser
          val article = parser.parse(doc)
          parseTime = System.currentTimeMillis() - parseTime

          println(s"Download: $downloadTime ms")
          println(s"Parse: $parseTime ms")

          Ok(Json.obj(
            "title" -> article.title,
            "text" -> article.contentHtml
          ))
        }
      )
  }

  def about = Action {
    Ok(views.html.about())
  }

}
