/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser.extractor

import org.scalatest.FunSuite
import rule.{WebTitleRule, WebDomainRule, HttpProtocolRule}

/**
 * The Class HttpProtocolExtractorTest.
 *
 * @author Nguyen Duc Dung
 * @since 5/1/12, 5:15 PM
 *
 */

class HttpProtocolExtractorTest extends FunSuite {

  val url1 = " http://vnexpress.net/gl/doi-song/2011/08/lang-phong-bi-tay-chay-khi-vao-dat-lien/ "
  val url2 = " https://www.google.com.vn/search?sourceid=chrome&ie=UTF-8&q=http+protocol "
  val url3 = ""
  val url4 = "         "
  val url5 = "      https://   http://  https://  "
  val url6 = "      sadsad://   sdasd://  sadsd://  "
  val url7 = "http://vnexpress.net"

  val title1 = "<title>dung ne</title>"

  val httpExtractor = new DefaultExtractor(new HttpProtocolRule())

  val webDomainExtractor = new DefaultExtractor(new WebDomainRule())

  val titleExtractor = new DefaultExtractor(new WebTitleRule())

  test("testTitleExtractor") {
    titleExtractor.extract(title1)

    assert("dung ne" == titleExtractor.results(0))
  }

  test("testWebDomainExtractor") {

    webDomainExtractor.extract(url1)
    assert(webDomainExtractor.results.length == 1)
    assert(webDomainExtractor.results(0) == "vnexpress.net")

    webDomainExtractor.results.clear()

    webDomainExtractor.extract(url2)
    assert(webDomainExtractor.results.length == 1)
    assert(webDomainExtractor.results(0) == "www.google.com.vn")

    webDomainExtractor.results.clear()

    webDomainExtractor.extract(url3)
    assert(webDomainExtractor.results.length == 0)

    webDomainExtractor.results.clear()

    webDomainExtractor.extract(url4)
    assert(webDomainExtractor.results.length == 0)

    webDomainExtractor.results.clear()

    webDomainExtractor.extract(url5)
    assert(webDomainExtractor.results.length == 0)

    webDomainExtractor.results.clear()

    webDomainExtractor.extract(url7)
    assert(webDomainExtractor.results(0) == "vnexpress.net")
  }


  test("testHttpExtract") {

    httpExtractor.extract(url1)
    httpExtractor.results.foreach(result => assert(result == "http"))

    httpExtractor.results.clear()

    httpExtractor.extract(url2)
    httpExtractor.results.foreach(result => assert(result == "https"))

    httpExtractor.results.clear()

    httpExtractor.extract(url3)
    assert(httpExtractor.results.size == 0)

    httpExtractor.results.clear()

    httpExtractor.extract(url4)
    assert(httpExtractor.results.size == 0)

    httpExtractor.results.clear()

    httpExtractor.extract(url5)
    assert(httpExtractor.results.length == 3)
    assert(httpExtractor.results(0) == "https")
    assert(httpExtractor.results(1) == "http")
    assert(httpExtractor.results(2) == "https")

    httpExtractor.results.clear()

    httpExtractor.extract(url6)
    httpExtractor.results.foreach(println)
    assert(httpExtractor.results.length == 0)
  }

}
