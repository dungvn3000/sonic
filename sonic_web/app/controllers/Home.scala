package controllers

import play.api.mvc.{Action, Controller}
import org.jsoup.Jsoup
import com.github.sonic.parser.ArticleParser
import play.api.libs.json.Json
import play.api.data.Form
import play.api.data.Forms._
import com.ning.http.client.AsyncHttpClient

/**
 * The Class Home.
 *
 * @author Nguyen Duc Dung
 * @since 6/12/13 10:39 PM
 *
 */
object Home extends Controller {

  def index = Action {
    Ok(views.html.home())
  }

  def parseUrl = Action {
    implicit request =>
      Form("url" -> nonEmptyText).bindFromRequest.fold(
        error => NotFound,
        url => {
          val httpClient = new AsyncHttpClient
          val response = httpClient.prepareGet(url).execute().get()
          val doc = Jsoup.parse(response.getResponseBodyAsStream, null, url)
          val parser = new ArticleParser
          val article = parser.parse(doc)
          Ok(Json.obj(
            "html" -> doc.html(),
            "text" -> article.contentHtml
          ))
        }
      )
  }

}
