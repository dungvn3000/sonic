package com.github.sonic.parser.util

import scala.io.Source
import java.util.regex.Pattern
import org.apache.commons.lang.StringUtils

/**
 * The Class Pattern.
 *
 * @author Nguyen Duc Dung
 * @since 6/15/13 11:08 AM
 *
 */
trait FilePattern {

  val regexFile: String

  lazy val regex = {
    val strm = try {
      this.getClass.getClassLoader.getResourceAsStream(regexFile)
    } catch {
      case _: Throwable => throw new IllegalArgumentException(s"Unavailable file $regexFile")
    }
    val src = Source.fromInputStream(strm)

    val sb = new StringBuilder
    src.getLines().filter(st => StringUtils.isNotBlank(st) && !st.startsWith("#")).foreach(st => {
      sb.append(st.trim + "|")
    })
    strm.close()
    val regex = sb.toString()

    //Remove last '|'
    regex.substring(0, regex.length - 1)
  }

  def pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)

  def matches(st: String) = pattern.matcher(st).matches()

  def contains(st: String) = StringUtils.isNotBlank(st) && regex.contains(StringUtils.trim(st))

}
