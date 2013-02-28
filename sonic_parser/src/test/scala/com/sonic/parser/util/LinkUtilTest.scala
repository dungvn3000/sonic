/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser.util

import org.scalatest.FunSuite
import LinkUtil._


/**
 * The Class LinkUtilTest.
 *
 * @author Nguyen Duc Dung
 * @since 5/1/12, 12:34 AM
 *
 */

class LinkUtilTest extends FunSuite {

  test("testIsUrl") {
    val url1 = "http://vnexpress.net/"
    val url2 = "http://e.v"
    val url3 = "http://.v"

    assert(isWebUrl(url1))
    assert(isWebUrl(url2))
    assert(!isWebUrl(url3))
  }

  test("testBaseUrl") {
    val url1 = "http://vnexpress.net/gl/doi-song/2011/08/lang-phong-bi-tay-chay-khi-vao-dat-lien/"
    val url2 = "dungvn3000@gmai.com"
    val url3 = "http://vnexpress.net?abcd/gl/"
    val url4 = "http://vnexpress.net/"
    assert("http://vnexpress.net" == LinkUtil.baseUrl(url1))
    assert("" == baseUrl(url2))
    assert("http://vnexpress.net" == LinkUtil.baseUrl(url3))
    assert("http://vnexpress.net" == LinkUtil.baseUrl(url4))
  }

  test("testcleanUrl") {
    val url1 = "  http://vnexprEss.net/GL/  "
    assert("http://vnexpress.net/gl" == cleanUrl(url1))
  }

}
