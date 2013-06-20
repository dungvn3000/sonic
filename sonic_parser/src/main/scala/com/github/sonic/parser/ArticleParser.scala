package com.github.sonic.parser

import model.Article
import org.jsoup.nodes.Document
import org.apache.commons.lang.StringUtils
import com.github.sonic.parser.controller.{VideoSiteController, AutoModeController}

/**
 * The Class ArticleParser.
 *
 * @author Nguyen Duc Dung
 * @since 12/23/12 1:45 AM
 *
 */
class ArticleParser {

  //The order of processor is very important, the parser only using the first match controller
  val controllers = List(
    new VideoSiteController,
    new AutoModeController
  )

  /**
   * Parse a html document to an article
   * @param doc
   * @return
   */
  def parse(doc: Document): Article = parse(doc, "")

  /**
   * Parse a html document with special title to an article
   * @param doc
   * @param title
   * @return
   */
  def parse(doc: Document, title: String) = {
    implicit val article = new Article(doc.normalise())
    if (StringUtils.isNotBlank(title)) article.title = title
    controllers.find(_.isRight).map(_.process)
    article
  }

}
