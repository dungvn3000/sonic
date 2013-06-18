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
    val url = "http://news.zing.vn/nhip-song-tre/si-tu-ha-noi-thi-lop-10-di-thi-tu-5h30/a328444.html#home_noibat1"
    val doc = Jsoup.connect(url).get()
    val parser = new ArticleParser
    val article = parser.parse(doc)
    println(article.contentHtml)
  }

}
