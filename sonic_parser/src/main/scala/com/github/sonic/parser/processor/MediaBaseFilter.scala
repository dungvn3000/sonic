package com.github.sonic.parser.processor

import com.github.sonic.parser.model.{ArticleElement, MediaElement, Article}

/**
 * Thi class will mark an image what has the previous or next potential element is become potential.
 *
 * @author Nguyen Duc Dung
 * @since 12/24/12 9:07 AM
 *
 */
class MediaBaseFilter(minImageTitleLength: Int = 2) extends Processor {
  def process(implicit article: Article) {
    var previous: Option[ArticleElement] = None
    var next: Option[ArticleElement] = None
    for (i <- 0 to article.elements.size - 1) {
      if (i - 1 >= 0) previous = Some(article.elements(i - 1))
      if (i + 1 <= article.elements.size - 1) next = Some(article.elements(i + 1))

      val element = article.elements(i)

      if (!element.isPotential && element.isInstanceOf[MediaElement]) {
        previous.map(prevElement => if (prevElement.isPotential) element.isPotential = true)
        next.map(nextElement => if (nextElement.isPotential) element.isPotential = true)
      }

      if (element.isPotential && element.isInstanceOf[MediaElement]) {
        //The image title should mark as a potential element.
        next.map(nextElement => if (element.isPotential && nextElement.text.length >= minImageTitleLength) nextElement.isPotential = true)
      }
    }
  }
}
