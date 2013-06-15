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
    val url = "http://tuoitre.vn/Giao-duc/Khoa-hoc/552051/hoc-sinh-cap%C2%A0iii-my-che-tau-ngam-ca-nhan-2-000-usd.html"
    val doc = Jsoup.connect(url).get()
    val parser = new ArticleParser
    val article = parser.parse(doc)
    println(article.contentHtml)
  }

}
