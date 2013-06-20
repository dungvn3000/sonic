package com.github.sonic.parser.controller

import com.github.sonic.parser.model.Article
import com.github.sonic.parser.processor.Processor

/**
 * The Class Controller.
 *
 * @author Nguyen Duc Dung
 * @since 12/23/12 3:37 AM
 *
 */
trait Controller extends Processor {

  val processors: List[Processor]

  def process(implicit article: Article) {
    processors.foreach(_.process)
  }

  def isRight(implicit article: Article): Boolean

}