package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article
import collection.JavaConversions._

/**
 * The Class DocumentCleaner.
 *
 * @author Nguyen Duc Dung
 * @since 12/25/12 2:08 PM
 *
 */
class DocumentCleaner extends Processor {
  def process(implicit article: Article) {
    val containerElement = article.containerElement
    val cleanedHtml = containerElement.html.replaceAll("&nbsp;", " ")
    containerElement.html(cleanedHtml)

    //Remove noscript tag
    val noScriptElement = containerElement.select("noscript")
    noScriptElement.remove()

    val scriptElement = containerElement.select("script")
    scriptElement.remove()

    val iframeElement = containerElement.select("iframe")
    iframeElement.filterNot(el => {
      val src = el.attr("src")
      src.contains("youtube.com")
    }).remove()
  }
}
