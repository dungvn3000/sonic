/*
 * Copyright (C) 2012 - 2013 Sonic Search Engine
 */

package com.sonic.parser.extractor.rule

/**
 * The Class HttpProtocolRule.
 *
 * @author Nguyen Duc Dung
 * @since 5/1/12, 4:11 PM
 *
 */

class HttpProtocolRule extends Rule {

  override val sign = ("", "://")

  override val mandatorySigns = List("http")

}
