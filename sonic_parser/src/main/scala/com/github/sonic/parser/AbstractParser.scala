/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser

import collection.mutable.ListBuffer
import org.jsoup.Jsoup
import collection.JavaConversions._

/**
 * The Class AbstractParser.
 *
 * @author Nguyen Duc Dung
 * @since 5/21/12, 1:41 PM
 *
 */

abstract class AbstractParser extends Parser {

  val selectors = new ListBuffer[Selector]


  def parse(html: String) {
    parse(html, "")
  }

  def parse(html: String, baseUrl: String) {

    beforeParse(html, baseUrl)

    val doc = Jsoup.parse(html, baseUrl)
    selectors.foreach(selector => {
      val elements = doc.select(selector.select)
      elements.foreach(element => {
        if (selector.attr == "text") {
          selector.result += element.text()
        } else {
          selector.result += element.attr(selector.attr)
        }
      })
    })

    afterParse(html, baseUrl)
  }

  protected def beforeParse(html: String, baseUrl: String) {

  }

  protected def afterParse(html: String, baseUrl: String) {

  }
}
