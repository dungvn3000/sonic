/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser.selector

import collection.mutable.ListBuffer

/**
 * The Class AbstractSelector.
 *
 * @author Nguyen Duc Dung
 * @since 5/21/12, 1:35 PM
 *
 */

abstract class AbstractSelector(_select: String, _attr: String) extends Selector {

  var _result = new ListBuffer[String]

  override def select = _select

  override def attr = _attr

  override def result = _result
}
