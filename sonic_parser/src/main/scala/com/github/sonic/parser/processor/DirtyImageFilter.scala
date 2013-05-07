package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article
import com.github.sonic.parser.util.DirtyImagePattern

/**
 * The Class ImageFilter.
 *
 * @author Nguyen Duc Dung
 * @since 12/24/12 9:15 PM
 *
 */
class DirtyImageFilter extends Processor {

  val dirtyImagePattern = new DirtyImagePattern

  def process(implicit article: Article) {
    article.imageElements.foreach(element => {
      if (dirtyImagePattern.matches(element.src)) {
        element.isPotential = false
      }
    })

  }
}
