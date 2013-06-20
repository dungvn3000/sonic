package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article
import com.github.sonic.parser.util.WhiteListPatten

/**
 * The Class WhiteListProcessor.
 *
 * @author Nguyen Duc Dung
 * @since 6/20/13 6:01 PM
 *
 */
class WhiteListProcessor extends Processor {
  def process(implicit article: Article) {
    val doc = article.doc
    WhiteListPatten.get(doc.baseUri()).foreach(select => {
      doc.select(select).attr("white-list", "true")
    })
  }
}
