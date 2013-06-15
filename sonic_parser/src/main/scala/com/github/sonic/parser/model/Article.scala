package com.github.sonic.parser.model

import org.jsoup.nodes.{Element, Document}
import org.apache.http.client.utils.URIUtils
import java.net.URI

/**
 * The Class Article.
 *
 * @author Nguyen Duc Dung
 * @since 12/23/12 1:00 AM
 *
 */
class Article(val doc: Document, private val _containerElement: Option[Element] = None) {

  def baseUrl = URIUtils.extractHost(new URI(doc.baseUri())).toURI

  /**
   * Default is vi.
   */
  var languageCode = "vi"

  var title = ""

  //This element will contain all text content element. Default is body element.
  var containerElement: Element = _containerElement.getOrElse(doc.body())

  /**
   * Article element list without title element.
   */
  var elements: List[ArticleElement] = Nil

  def textElements: List[TextElement] = elements.filter(_.isInstanceOf[TextElement]).map(_.asInstanceOf[TextElement])

  def mediaElements: List[MediaElement] = elements.filter(_.isInstanceOf[MediaElement]).map(_.asInstanceOf[MediaElement])

  def linkElements: List[LinkElement] = elements.filter(_.isInstanceOf[LinkElement]).map(_.asInstanceOf[LinkElement])

  def potentialElements: List[ArticleElement] = elements.filter(_.isPotential)

  def contentElements: List[ArticleElement] = elements.filter(_.isContent)

  def textContentElements: List[ArticleElement] = textElements.filter(_.isContent)

  def jsoupElements = elements.map(_.jsoupElement)

  def images = contentElements.filter(_.tagName == "img")

  def text = {
    val sb = new StringBuilder
    textContentElements.foreach(element => {
      sb.append(element.text)
      sb.append("\n")
    })
    sb.toString()
  }

  def contentHtml = {
    val sb = new StringBuilder
    contentElements.foreach(element => if(!element.isTitle) {
      sb.append("<div>" + element.jsoupElement.outerHtml() + "</div>")
      sb.append("\n")
    })
    sb.toString()
  }

  override def toString = text
}
