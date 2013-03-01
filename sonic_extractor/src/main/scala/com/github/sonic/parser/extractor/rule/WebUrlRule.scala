package com.github.sonic.parser.extractor.rule


/**
 * The Class WebUrlRule.
 *
 * @author Nguyen Duc Dung
 * @since 3/1/13 10:34 AM
 *
 */
class WebUrlRule extends Rule {

  override val sign = ("http", " ")

  override val keepSign = true

  override val mandatorySigns = List("http", ".", "://")

  override val beforeExtractRule = List(
    (st: String) => {
      if (st.length > minimumLength && st.last != ' ') st + " "
      else st
    }
  )
}
