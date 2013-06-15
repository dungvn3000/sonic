package com.github.sonic.parser.processor

import collection.mutable.ListBuffer
import com.github.sonic.parser.model._
import collection.JavaConversions._
import com.github.sonic.parser.model.Article
import JsoupElementWrapper._

/**
 * The Class ArticleExtractor.
 *
 * @author Nguyen Duc Dung
 * @since 12/23/12 3:26 AM
 *
 */
class ArticleExtractor extends Processor {

  def process(implicit article: Article) {
    implicit val articleElements = new ListBuffer[ArticleElement]
    val elements = article.containerElement.getAllElements

    elements.foreach(element => if (!element.isSkipParse) element match {
      case TextElementMatcher(el) => addToArticle(new TextElement(el))
      case MediaElementMatcher(el) => addToArticle(new MediaElement(el))
      case LinkElementMatcher(el) => addToArticle(new LinkElement(el))
      case _ => //ignore
    })

    article.elements = articleElements.toList
  }

  private def addToArticle(element: ArticleElement)(implicit articleElements: ListBuffer[ArticleElement]) {
    //Skip all children
    element.jsoupElement.children().foreach(_.isSkipParse = true)
    element.index = articleElements.size
    articleElements += element
  }

}