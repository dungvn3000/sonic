package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article
import org.apache.commons.lang.StringUtils

/**
 * The Class TagBaseFilter.
 *
 * @author Nguyen Duc Dung
 * @since 12/26/12 12:24 PM
 *
 */
class TagBaseFilter extends Processor {
  def process(implicit article: Article) {
    article.potentialElements.foreach(potentialElement => {
      val tagName = potentialElement.tagName
      val elementClass = potentialElement.className
      article.elements.foreach(el => {
        if (el.tagName == tagName
          && el.className == elementClass
          && (StringUtils.isNotBlank(elementClass) || el.parent == potentialElement.parent)
          && el.hasText) {
          el.isPotential = true
        }

        if (el.tagName == tagName
          && el.parent == potentialElement.parent
          && el.isSimilarClassName(potentialElement)) {
          el.isPotential = true
        }

      })
    })
  }
}
