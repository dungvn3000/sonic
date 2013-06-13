package com.github.sonic.parser

import org.junit.Test
import org.cyberneko.html.parsers.SAXParser
import com.github.sonic.parser.util.FileUtil._
import org.xml.sax.InputSource
import com.github.sonic.parser.handler.SonicHtmlContentHandler

/**
 * The Class TestApi.
 *
 * @author Nguyen Duc Dung
 * @since 6/13/13 11:27 PM
 *
 */
class TestApi {

  @Test
  def testApi() {
    val parser = new SAXParser
    parser.setContentHandler(new SonicHtmlContentHandler)
    parser.parse(new InputSource(getResourceAsStream("vnexpress1.html")))
  }


}
