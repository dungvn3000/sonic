package com.github.sonic.parser.handler

import org.xml.sax.{Locator, Attributes, ContentHandler}

/**
 * The Class SonicHtmlContentHandler.
 *
 * @author Nguyen Duc Dung
 * @since 6/14/13 1:13 AM
 *
 */
class SonicHtmlContentHandler extends ContentHandler {

  def setDocumentLocator(locator: Locator) {}

  def startDocument() {}

  def endDocument() {}

  def startPrefixMapping(prefix: String, uri: String) {}

  def endPrefixMapping(prefix: String) {}

  def startElement(uri: String, localName: String, qName: String, atts: Attributes) {
  }

  def endElement(uri: String, localName: String, qName: String) {}

  def characters(ch: Array[Char], start: Int, length: Int) {}

  def ignorableWhitespace(ch: Array[Char], start: Int, length: Int) {}

  def processingInstruction(target: String, data: String) {}

  def skippedEntity(name: String) {}
}
