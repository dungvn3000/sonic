/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.github.sonic.parser.model.html

import collection.mutable.ListBuffer

/**
 * The Class Node.
 *
 * @author Nguyen Duc Dung
 * @since 5/17/12, 1:03 AM
 *
 */

class Node {
  var attributes = new ListBuffer[Attribute]
  var childs = new ListBuffer[Node]
}
