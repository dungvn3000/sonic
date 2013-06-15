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
    val url = "http://xinhxinh.com.vn/phong-cach/20130613100611317/ao-co-sen-chua-bao-gio-loi-mot.xinh"
    val doc = Jsoup.connect(url).get()
    val parser = new ArticleParser
    val article = parser.parse(doc)
    println(article.contentHtml)
  }

}
