/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser.extractor

import rule.Rule
import scala.util.control.Breaks._
import collection.mutable.ListBuffer

/**
 * The Class AbstractExtractor.
 *
 * @author Nguyen Duc Dung
 * @since 5/1/12, 4:15 PM
 *
 */

abstract class AbstractExtractor extends Extractor {

  val rule: Rule

  def beforeExtract(st: String, rule: Rule): String = {
    var result = st
    if (rule.beforeExtractRule != null)
      rule.beforeExtractRule.foreach(ruleFunc => result = ruleFunc(result))
    result
  }

  def afterExtract(st: String, rule: Rule): String = {
    var result = st
    if (rule.afterExtractRule != null)
      rule.afterExtractRule.foreach(ruleFunc => result = ruleFunc(result))
    result
  }

  def extract(_st: String) {
    val st = beforeExtract(_st, rule)
    if (st.length > rule.minimumLength) {
      process(st, rule).foreach(result => {
        if (validate(result, rule)) {
          results += afterExtract(result, rule)
        }
      })
    }
  }

  private def process(st: String, rule: Rule): List[String] = {
    var process = st
    var result = ""

    var results = new ListBuffer[String]

    val beginWith = rule.sign._1
    val endWith = rule.sign._2

    var beginIndex = 0
    var endIndex = st.length - 1

    while (beginIndex >= 0 && endIndex > 0) {
      if (beginWith.length > 0) {
        beginIndex = process.indexOf(beginWith)
      }
      if (endWith.length > 0 && beginIndex >= 0) {
        endIndex = process.substring(beginIndex + beginWith.length, process.length).indexOf(endWith)
        if (endIndex >= 0) endIndex += beginIndex + beginWith.length
      }

      if (endIndex > beginIndex && beginIndex >= 0) {
        result = process.substring(beginIndex + beginWith.length, endIndex)
        //Remove found result then find another one
        process = process.substring(0, beginIndex) + process.substring(endIndex
          + endWith.length, process.length)
        results += result
      }
    }

    results.toList
  }

  private def validate(st: String, rule: Rule): Boolean = {
    //Check dirty word
    var isDirty = false
    if (rule.dirtySigns != null)
      breakable(
        rule.dirtySigns.foreach(dirtySign => {
          if (st.contains(dirtySign)) {
            isDirty = true
            break()
          }
        })
      )

    var isError = false
    //Check mandatory word
    if (rule.mandatorySigns != null)
      breakable(
        rule.mandatorySigns.foreach(sign => {
          if (!st.contains(sign)) {
            isError = true
            break()
          }
        })
      )

    !isDirty && !isError
  }

}
