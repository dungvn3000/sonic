package com.github.sonic.parser

import org.junit.Test

/**
 * The Class TestRssExtractor.
 *
 * @author Nguyen Duc Dung
 * @since 6/16/13 4:10 PM
 *
 */
class TestRssExtractor {

  @Test
  def testRssExtractor() {
    val rssExtractor = new RssExtractor
    rssExtractor.parse("http://www.thanhnien.com.vn/pages/default.aspx")
  }

}
