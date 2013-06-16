package com.github.sonic.parser

import scala.collection.mutable.ListBuffer
import com.ning.http.client.{AsyncHttpClient, AsyncHttpClientConfig}
import org.jsoup.Jsoup
import collection.JavaConversions._
import org.apache.http.client.utils.URIUtils
import java.net.URI
import edu.uci.ics.crawler4j.url.URLCanonicalizer
import org.apache.commons.validator.routines.UrlValidator
import org.apache.commons.lang.StringUtils

/**
 * The Class RssExtractor.
 *
 * @author Nguyen Duc Dung
 * @since 6/16/13 3:23 PM
 *
 */
class RssExtractor {

  lazy val config = new AsyncHttpClientConfig.Builder()
    .setCompressionEnabled(true)
    .setFollowRedirects(true)
    .build

  val types = Seq("application/rss+xml", "application/atom+xml")
  val rel = "alternate"

  val urlValidator = new UrlValidator(Array("http", "https"))

  val rss = new ListBuffer[String]

  def parse(url: String) {
    val httpClient = new AsyncHttpClient(config)
    val response = httpClient.prepareGet(url).execute().get()
    val doc = Jsoup.parse(response.getResponseBodyAsStream, null, url)
    val links = doc.select(s"link[rel=$rel]")
    val baseUrl = URIUtils.extractHost(new URI(url)).toURI
    if (baseUrl != null) {
      links.foreach(link => {
        val linkType = link.attr("type")
        if (types.contains(linkType)) {
          val href = link.attr("href")
          add(href, baseUrl)
        }
      })
    }
  }

  def add(url: String, baseUrl: String) {
    val fixedUrl = URLCanonicalizer.getCanonicalURL(url, baseUrl)
    if (StringUtils.isNotBlank(fixedUrl) && urlValidator.isValid(fixedUrl)) {
      rss += fixedUrl
    }
  }

}
