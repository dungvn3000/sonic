package com.github.sonic.parser.model

import org.jsoup.nodes.Element

/**
 * The Class ImageBlock.
 *
 * @author Nguyen Duc Dung
 * @since 12/23/12 1:04 AM
 *
 */
class MediaElement(_jsoupElement: Element)(implicit article: Article) extends ArticleElement(_jsoupElement) {

  def text = ""

  def src = jsoupElement.attr("src")

  /**
   * @return 100 when the element is potential.
   */
  override def score = if (isPotential) 100 else super.score

  override def toString = src
}

object MediaElementMatcher {
  def unapply(jsoupElement: Element) = jsoupElement.tagName.toLowerCase match {
    case "img" | "object" | "embed" => Some(jsoupElement)
    case "iframe" => {
      val src = jsoupElement.attr("src")
      if (src.contains("youtube.com")) Some(jsoupElement) else None
    }
    case _ => None
  }
}