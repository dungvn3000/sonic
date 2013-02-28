/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.github.sonic.parser.model.html

import collection.mutable.ListBuffer

/**
 * The Class Document.
 *
 * @author Nguyen Duc Dung
 * @since 5/17/12, 1:03 AM
 *
 */

class Document {
  var title: String = _
  var content: String = _
  var url: String = _
  var baseUrl: String = _
  var nodes = new ListBuffer[Node]
}
