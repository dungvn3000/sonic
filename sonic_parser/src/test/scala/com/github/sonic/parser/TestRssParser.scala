package com.github.sonic.parser

import org.junit.Test
import com.ning.http.client.{AsyncHttpClient, AsyncHttpClientConfig}
import collection.JavaConversions._
import com.sun.syndication.io.{XmlReader, SyndFeedInput}
import com.sun.syndication.feed.synd.SyndEntry

/**
 * The Class TestRssParser.
 *
 * @author Nguyen Duc Dung
 * @since 6/18/13 7:12 PM
 *
 */
class TestRssParser {

  lazy val config = new AsyncHttpClientConfig.Builder()
    .setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
    .setCompressionEnabled(true)
    .setFollowRedirects(true)
    .build

  @Test
  def testRssParser() {
    val url = "http://westart.vn/feed/"
    val httpClient = new AsyncHttpClient(config)
    val response = httpClient.prepareGet(url).execute().get()
    val input = new SyndFeedInput
    val feed = input.build(new XmlReader(response.getResponseBodyAsStream))
    val entries = feed.getEntries

    entries.foreach(entry => {
      println(entry.asInstanceOf[SyndEntry].getDescription.getValue)
    })

  }

}
