package controllers

import play.api.mvc.{Action, Controller}

/**
 * The Class Home.
 *
 * @author Nguyen Duc Dung
 * @since 6/12/13 10:39 PM
 *
 */
object Home extends Controller {

  def index = Action {
    Ok("Hello World")
  }

}
