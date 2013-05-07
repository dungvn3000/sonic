package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article

/**
 * The Class RemoveHighLinkDensityElement.
 *
 * @author Nguyen Duc Dung
 * @since 12/24/12 10:32 PM
 *
 */
class HighLinkDensityFilter extends Processor {
  def process(implicit article: Article) {
    article.textElements.foreach(element => {
      if (element.isHighLinkDensity) {
        element.isPotential = false
      }
    })
  }
}
