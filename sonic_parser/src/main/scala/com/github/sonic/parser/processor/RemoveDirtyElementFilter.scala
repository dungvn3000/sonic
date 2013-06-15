package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article
import com.github.sonic.parser.util.DirtyElementPattern
import collection.JavaConversions._

/**
 * The Class RemoveDirtyElementFilter. This filter should only using for manual mode,
 * because i might take bad effect result of auto mode.
 *
 * @author Nguyen Duc Dung
 * @since 12/29/12 5:53 PM
 *
 */
class RemoveDirtyElementFilter extends Processor {

  def process(implicit article: Article) {
    val removeElements = article.containerElement.getAllElements.filter(element => {
      DirtyElementPattern.matches(element.className) || DirtyElementPattern.matches(element.id)
    })

    removeElements.foreach(_.remove())
  }
}
