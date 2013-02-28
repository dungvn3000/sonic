/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.github.sonic.parser.extractor.rule

/**
 * The Class WebTitleRule.
 *
 * @author Nguyen Duc Dung
 * @since 5/2/12, 2:47 PM
 *
 */

class WebTitleRule extends Rule {
  override val sign = ("<title>", "</title>")
}
