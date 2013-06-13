package com.github.sonic.parser.processor

import com.github.sonic.parser.model.{ImageElement, Article}
import org.apache.commons.lang.StringUtils
import org.apache.commons.validator.routines.UrlValidator
import edu.uci.ics.crawler4j.url.URLCanonicalizer

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
      element match {
        case image: ImageElement =>
          //Fix relative url.
          if (StringUtils.isNotBlank(image.src)) {
            val urlValidator = new UrlValidator(Array("http", "https"))
            if (article.baseUrl != null) {
              val imageUrl = URLCanonicalizer.getCanonicalURL(image.src, article.baseUrl)
              if (StringUtils.isNotBlank(imageUrl) && urlValidator.isValid(imageUrl)) {
                image.jsoupElement.attr("src", imageUrl)
              }
            }
          }
        case _ =>
      }
    })
  }
}
