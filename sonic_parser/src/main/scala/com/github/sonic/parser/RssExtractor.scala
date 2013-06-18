package com.github.sonic.parser

import com.ning.http.client.{AsyncHttpClient, AsyncHttpClientConfig}
import org.jsoup.Jsoup
import collection.JavaConversions._
import org.apache.http.client.utils.URIUtils
import java.net.URI
import edu.uci.ics.crawler4j.url.URLCanonicalizer
import org.apache.commons.validator.routines.UrlValidator
import org.apache.commons.lang.StringUtils
import scala.util.control.Breaks._
import org.apache.http.HttpStatus
import org.horrabin.horrorss.RssParser

/**
 * The Class RssExtractor.
 *
 * @author Nguyen Duc Dung
 * @since 6/16/13 3:23 PM
 *
 */
class RssExtractor {

  lazy val config = new AsyncHttpClientConfig.Builder()
    .setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
    .setCompressionEnabled(true)
    .setFollowRedirects(true)
    .build

  val types = Seq("application/rss+xml", "application/atom+xml")
  val rel = "alternate"

  val urlValidator = new UrlValidator(Array("http", "https"))

  var rssUrl: Option[String] = None
  var rssName: Option[String] = None

  def parse(url: String) {
    val httpClient = new AsyncHttpClient(config)
    val response = httpClient.prepareGet(url).execute().get()
    if (response.getStatusCode == HttpStatus.SC_OK) {
      val doc = Jsoup.parse(response.getResponseBodyAsStream, null, url)
      val links = doc.select(s"link[rel=$rel]")
      val baseUrl = URIUtils.extractHost(new URI(url)).toURI
      if (baseUrl != null) {
        breakable {
          links.foreach(link => {
            val linkType = link.attr("type")
            if (types.contains(linkType)) {
              val href = link.attr("href")
              val fixedUrl = URLCanonicalizer.getCanonicalURL(href, baseUrl)
              if (StringUtils.isNotBlank(fixedUrl) && urlValidator.isValid(fixedUrl)) {
                rssUrl = Some(fixedUrl)
                break()
              }
            }
          })
        }
      }
    }

    rssUrl.map(url => {
      val response = httpClient.prepareGet(url).execute().get()
      if (response.getStatusCode == HttpStatus.SC_OK) {
        val rssParser = new RssParser
        val feed = rssParser.load(response.getResponseBodyAsStream)
        rssName = Some(feed.getChannel.getTitle)
      }
    })
  }

}
