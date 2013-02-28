/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser

import org.scalatest.FunSuite
import org.jsoup.Jsoup
import collection.JavaConversions._
import selector.DefaultSelector

/**
 * The Class LinkParserTest.
 *
 * @author Nguyen Duc Dung
 * @since 5/21/12, 10:52 AM
 *
 */

class LinkParserTest extends FunSuite {

  val html1 = "<html>" +
                "<body>" +
                    "<a href='/news/'>News</a>" +
                    "<a href='http://jsoup.org/cookbook/extracting-data/working-with-urls'></a>" +
                "</body>" +
            "</html>"

  val html2 = "<html>" +
                "<body>" +
                    "<div class ='a'>A1</div>" +
                    "<div class ='a'>A2</div>" +
                "</body>" +
              "</html>"

  test("testLinkParse") {
    val linkParser = new LinkParser
    linkParser.parse(html1, "http://vnexpress.net/")
    assert(linkParser.result.size == 2)
    assert(linkParser.result(0) == "http://vnexpress.net/news")
    assert(linkParser.result(1) == "http://jsoup.org/cookbook/extracting-data/working-with-urls")
  }


  test("testParse") {
    val defaultParser = new DefaultParser
    val linkSelector = new DefaultSelector("div.a:eq(0)")
    defaultParser.selectors += linkSelector

    defaultParser.parse(html2)

    assert(linkSelector.result.size == 1)
    assert(linkSelector.result(0) == "A1")
  }

}
