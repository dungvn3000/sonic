package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article

/**
 * This filter just simple mark all potential elements is content.
 *
 * @author Nguyen Duc Dung
 * @since 12/28/12 5:35 PM
 *
 */
class MarkPotentialIsContentFilter extends Processor {
  def process(implicit article: Article) {
    article.potentialElements.foreach(_.isContent = true)
  }
}
