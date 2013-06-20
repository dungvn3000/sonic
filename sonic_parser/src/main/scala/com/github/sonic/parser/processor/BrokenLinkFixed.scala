package com.github.sonic.parser.processor

import com.github.sonic.parser.model.Article
import org.apache.commons.lang.StringUtils
import org.apache.commons.validator.routines.UrlValidator
import edu.uci.ics.crawler4j.url.URLCanonicalizer
import collection.JavaConversions._
import scalaz._
import Scalaz._

/**
 * The processor is using for fix relative link.
 * Expamle: /abc => www.ecxample.com/abc
 *
 * @author Nguyen Duc Dung
 * @since 6/13/13 5:22 PM
 *
 */
class BrokenLinkFixed extends Processor {
  def process(implicit article: Article) {
    article.contentElements.foreach(element => {
      element.jsoupElement.getAllElements.foreach(jsoupEl => {
        if (jsoupEl.tagName == "a" || jsoupEl.tagName == "img") {
          val src = jsoupEl.attr("src")
          val href = jsoupEl.attr("href")
          val url = src.isEmpty ? href | src
          val attr = src.isEmpty ? "href" | "src"
          //Fix relative url.
          if (StringUtils.isNotBlank(url)) {
            val urlValidator = new UrlValidator(Array("http", "https"))
            if (article.baseUrl != null) {
              //Fix url has a white space
              val cleanUrl = StringUtils.trim(url.replace(" ", "%20"))
              val fixedUrl = URLCanonicalizer.getCanonicalURL(cleanUrl, article.baseUrl)
              if (StringUtils.isNotBlank(fixedUrl) && urlValidator.isValid(fixedUrl)) {
                jsoupEl.attr(attr, fixedUrl)
              }
            }
          }
        }

        if (jsoupEl.tagName == "a") {
          jsoupEl.attr("target", "_blank")
        }

      })
    })
  }
}
