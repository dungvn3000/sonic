package com.github.sonic.parser.processor.youtube

import com.github.sonic.parser.processor.Processor
import com.github.sonic.parser.model.{ArticleElement, MediaElement, TextElement, Article}
import scala.collection.JavaConversions._
import org.apache.commons.lang.StringUtils
import org.jsoup.Jsoup
import scala.collection.mutable.ListBuffer

/**
 * The Class YoutubeExtractor.
 *
 * @author Nguyen Duc Dung
 * @since 6/19/13 11:00 PM
 *
 */
class YoutubeExtractor extends Processor {
  def process(implicit article: Article) {
    val doc = article.doc
    val titleEl = doc.select("meta[name=title]")
    val descriptionEl = doc.select("meta[name=description]")
    val playerEl = doc.select("meta[name=twitter:player]")
    val thumpEl = doc.select("meta[name=twitter:image]")

    var html = ""

    titleEl.headOption.map(_.attr("content")).map(title => {
      article.title = title
    })

    playerEl.headOption.map(_.attr("content")).map(embedUrl => {
      html += s"<iframe width='480' height='270' src='$embedUrl?feature=oembed' frameborder='0' allowfullscreen></iframe>"
    }).getOrElse({
      thumpEl.headOption.map(_.attr("content")).map(url => {
        html += s"<img src='$url'><img>"
      })
    })

    descriptionEl.headOption.map(_.attr("content")).map(description => {
      html += s"<p>$description</p>"
    })

    if (StringUtils.isNotBlank(html)) {
      val elements = new ListBuffer[ArticleElement]
      val newDoc = Jsoup.parseBodyFragment(html, doc.baseUri())
      newDoc.select("iframe, img").foreach(el => {
        val mediaEl = new MediaElement(el)
        mediaEl.isContent = true
        elements += mediaEl
      })
      newDoc.select("p").foreach(el => {
        val textEl = new TextElement(el)
        textEl.isContent = true
        elements += textEl
      })
      article.elements = elements.toList
    }
  }
}
