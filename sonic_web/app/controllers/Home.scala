package controllers

import play.api.mvc.{Action, Controller}
import org.jsoup.Jsoup
import com.github.sonic.parser.ArticleParser
import play.api.libs.json.Json

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

  def parse(url: String) = Action {
    val doc = Jsoup.connect(url).get
    val parser = new ArticleParser
    val article = parser.parse(doc)
    Ok(Json.obj(
      "html" -> doc.html(),
      "text" -> article.text
    ))
  }

}
