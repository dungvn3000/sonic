/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.github.sonic.parser.extractor

import rule.Rule

/**
 * The Class DefaultExtractor.
 *
 * @author Nguyen Duc Dung
 * @since 5/20/12, 9:51 PM
 *
 */

class DefaultExtractor(_rule: Rule) extends AbstractExtractor {
  val rule = _rule
}
