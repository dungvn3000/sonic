/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser.extractor.rule


/**
 * The Class WebDomainRule.
 *
 * @author Nguyen Duc Dung
 * @since 5/2/12, 12:34 AM
 *
 */

class WebDomainRule extends Rule {

  override val sign = ("://", "/")

  override val dirtySigns = List(":")

  override val mandatorySigns = List(".")

  override val beforeExtractRule = List(
    (st: String) => {
      if (st.length > minimumLength && st.last != '/') st.trim + "/"
      else st.trim
    }
  )
}
