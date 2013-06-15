package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article
import com.github.sonic.parser.util.DirtyTextPattern

/**
 * This filter will mark all element such as comment, relate news, feedback is not content.
 *
 * @author Nguyen Duc Dung
 * @since 6/15/13 11:33 AM
 *
 */
class CommonElementFilter extends Processor {
  def process(implicit article: Article) {
    article.textElements.foreach(element => {
      if(DirtyTextPattern.contains(element.text)) {
        element.isPotential = false
      }
    })
  }
}
