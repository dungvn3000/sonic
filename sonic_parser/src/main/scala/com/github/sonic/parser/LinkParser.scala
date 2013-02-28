/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.github.sonic.parser

import collection.mutable.ListBuffer
import selector.DefaultSelector
import util.LinkUtil

/**
 * The Class LinkParser.
 *
 * @author Nguyen Duc Dung
 * @since 4/30/12, 11:41 PM
 *
 */

class LinkParser extends AbstractParser {

  selectors += new DefaultSelector("a", "abs:href")

  override protected def afterParse(html: String, baseUrl: String) {
    var urls = new ListBuffer[String]
    selectors(0).result.foreach(url => {
      urls += LinkUtil.cleanUrl(url)
    })
    selectors(0).result.clear()
    selectors(0).result ++= urls
  }

  def result = {
    selectors(0).result
  }
}
