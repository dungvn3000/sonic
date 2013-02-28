/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.github.sonic.parser.model.html

import reflect.BeanProperty

/**
 * The Class Attribute.
 *
 * @author Nguyen Duc Dung
 * @since 5/17/12, 1:05 AM
 *
 */

class Attribute {
  @BeanProperty var name: String = _
  @BeanProperty var value: String = _

  def this(name: String, value: String) {
    this()
    this.name = name
    this.value = value
  }
}
