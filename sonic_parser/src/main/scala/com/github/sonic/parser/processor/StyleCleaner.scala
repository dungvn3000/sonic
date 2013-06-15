package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article
import collection.JavaConversions._

/**
 * This class will remove all class of element.
 *
 * @author Nguyen Duc Dung
 * @since 6/15/13 12:13 PM
 *
 */
class StyleCleaner extends Processor {
  def process(implicit article: Article) {
    article.contentElements.foreach(element => {
      element.jsoupElement.getAllElements.foreach(jsoupEl => {
        jsoupEl.removeAttr("class")
      })
    })
  }
}
