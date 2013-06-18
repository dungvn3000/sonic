package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article
import org.apache.commons.lang.StringUtils

/**
 * This class will remove element with same content
 *
 * @author Nguyen Duc Dung
 * @since 6/18/13 8:12 AM
 *
 */
class DuplicateCleaner extends Processor {
  def process(implicit article: Article) {
    article.contentElements.foreach(el1 => {
      article.contentElements.foreach(el2 => {
        if (el1 != el2 && el1.isContent
          && StringUtils.isNotBlank(el1.html) && el1.html == el2.html) {
          el2.isContent = false
        }
      })
    })
  }
}
