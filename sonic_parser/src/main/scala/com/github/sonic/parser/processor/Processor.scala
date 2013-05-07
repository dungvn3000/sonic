package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article

/**
 * The Class Processor.
 *
 * @author Nguyen Duc Dung
 * @since 12/23/12 3:35 AM
 *
 */
trait Processor {
  def process(implicit article: Article)
}