/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.github.sonic.parser.util

import scala.util.control.Breaks._
import com.github.sonic.parser.extractor.DefaultExtractor
import com.github.sonic.parser.extractor.rule.WebDomainRule

/**
 * The Class LinkUtil.
 *
 * @author Nguyen Duc Dung
 * @since 4/30/12, 11:52 PM
 *
 */

object LinkUtil {

  val slashChar = '/'
  val breakDownSign = List(('.'), ('/'), ('?'), ('='), ('#'), ('\'', '\''))
  val dirtyChar = List('@', ':', '(', ')')
  val httpProtocolSign = List("http://", "https://")
  val urlMinLength = 9

  /**
   * Check a url is web url or not
   * @param url web url
   * @return
   */
  def isWebUrl(url: String) = {
    val checkUrl = url.trim
    var result = false
    if (checkUrl.length > urlMinLength) {
      breakable(
        httpProtocolSign.foreach(sign => {
          result = checkUrl.startsWith(sign)
          if (result) break()
        })
      )
    }
    result
  }

  /**
   * Get base url of a web url
   * @param url web url
   * @return empty string if can not find base url
   */
  def baseUrl(url: String): String = {
    var baseUrl = ""
    if (isWebUrl(url)) {
      val commaIndex = url.indexOf(".")
      if (commaIndex > 0) {
        var separatorIndex = 0
        breakable {
          for (i <- commaIndex to url.length - 1) {
            val char = url.charAt(i)
            if (char == '/' || char == '?' || char == '#') {
              separatorIndex = i
              break()
            }
          }
        }

        val firstPart = url.substring(0, commaIndex)
        var secondPart = ""
        if (separatorIndex > 0) secondPart = url.substring(commaIndex, separatorIndex)
        else return url //Because the url is already base url
        baseUrl = firstPart + secondPart
      }
    }
    baseUrl
  }

  /**
   * Get domain name from a url
   * @param url web url
   * @return
   */
  def domainName(url: String): String = {
    val webDomainExtractor = new DefaultExtractor(new WebDomainRule())
    webDomainExtractor.extract(url)
    if (webDomainExtractor.results.size > 0) return webDomainExtractor.results(0)
    ""
  }


  /**
   * Clean url
   * @param url web url
   */
  def cleanUrl(url: String): String = {
    var cleanUrl = url
    if (isWebUrl(url)) {
      if (url.trim.last == slashChar) cleanUrl = url.trim.dropRight(1)
      return cleanUrl.trim.toLowerCase
    }
    cleanUrl
  }
}
