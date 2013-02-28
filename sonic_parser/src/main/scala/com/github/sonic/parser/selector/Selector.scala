/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.github.sonic.parser.selector

import collection.mutable.ListBuffer

/**
 * The Class Selector.
 *
 * @author Nguyen Duc Dung
 * @since 5/21/12, 1:33 PM
 *
 */

trait Selector {

  def select: String

  def attr: String

  def result: ListBuffer[String]

}
