package com.github.sonic.parser

import org.junit.Test
import org.jsoup.Jsoup


/**
 * The Class TestArticleParser.
 *
 * @author Nguyen Duc Dung
 * @since 6/15/13 4:18 AM
 *
 */
class TestArticleParser {

  @Test
  def testParser() {
    val url = "http://mashable.com/2013/06/19/jimmy-kimmel-channing-all-over-your-tatum/?utm_campaign=Feed%3A+Mashable+%28Mashable%29&utm_cid=Mash-Product-RSS-Pheedo-All-Partial&utm_medium=feed&utm_source=feedburner"
    val doc = Jsoup.connect(url).get()
    val parser = new ArticleParser
    val article = parser.parse(doc)
    println(article.contentHtml)
  }

}
