/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser.extractor

import collection.mutable.ListBuffer

/**
 * The Class Extractor.
 *
 * @author Nguyen Duc Dung
 * @since 5/1/12, 3:58 PM
 *
 */

trait Extractor {

  var results = new ListBuffer[String]

  def extract(st: String)

}
