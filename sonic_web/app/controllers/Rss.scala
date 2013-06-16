package controllers

import play.api.mvc.{Action, Controller}
import play.api.data.Form
import play.api.data.Forms._
import com.github.sonic.parser.RssExtractor
import play.api.libs.json.Json

/**
 * The Class Rss.
 *
 * @author Nguyen Duc Dung
 * @since 6/16/13 3:54 PM
 *
 */
object Rss extends Controller {

  def index = Action {
    Ok(views.html.rss())
  }

  def extractRss = Action {
    implicit request =>
      Form("url" -> nonEmptyText).bindFromRequest.fold(
        error => NotFound,
        url => {
          val rssExtractor = new RssExtractor
          rssExtractor.parse(url)
          Ok(Json.arr(rssExtractor.rss))
        }
      )
  }

}
