/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.github.sonic.parser.extractor.rule

/**
 * The Class Rule.
 *
 * @author Nguyen Duc Dung
 * @since 5/1/12, 3:58 PM
 *
 */

trait Rule {

  val sign: (String, String)

  val dirtySigns: List[String] = Nil

  val mandatorySigns: List[String] = Nil

  val keepSign = false

  val beforeExtractRule: List[String => String] = List(
    (st: String) => st.trim
  )

  val afterExtractRule: List[String => String] = List(
    (st: String) => st.trim
  )

  def minimumLength: Int = {
    var beginSignLength = 0
    if (sign._1 != null) beginSignLength = sign._1.length
    var endSignLength = 0
    if (sign._2 != null) endSignLength = sign._2.length

    beginSignLength + endSignLength
  }
}
