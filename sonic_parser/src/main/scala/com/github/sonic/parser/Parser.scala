/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser

/**
 * The Class Parser.
 *
 * @author Nguyen Duc Dung
 * @since 5/21/12, 1:29 PM
 *
 */

trait Parser {

  def parse(html: String, baseUrl: String)

  def parse(html: String)

}
