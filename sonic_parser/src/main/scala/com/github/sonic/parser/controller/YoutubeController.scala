package com.github.sonic.parser.controller

import com.github.sonic.parser.model.Article
import com.github.sonic.parser.processor.youtube.YoutubeExtractor

/**
 * The Class YoutubeController.
 *
 * @author Nguyen Duc Dung
 * @since 6/19/13 10:47 PM
 *
 */
class YoutubeController extends Controller {

  val processors = List(new YoutubeExtractor)

  def isRight(implicit article: Article) = if (article.baseUrl != null
    && article.doc.baseUri().contains("youtube.com")) true else false

}
