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
    val url = "http://www.thanhnien.com.vn/Pages/20130521/galaxy-tab-se-co-phien-ban-dung-vi-xu-ly-intel.aspx"
    val doc = Jsoup.connect(url).get()
    val parser = new ArticleParser
    val article = parser.parse(doc)
    println(article.contentHtml)
  }

}
