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
    val url = "http://mp3.zing.vn/album/Neu-Co-Quay-Ve-Minh-Vuong-M4U/ZWZABD88.html"
    val doc = Jsoup.connect(url).get()
    val parser = new ArticleParser
    val article = parser.parse(doc)
    println(article.contentHtml)
  }

}
