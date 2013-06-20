package com.github.sonic.parser.controller

import com.github.sonic.parser.model.Article
import com.github.sonic.parser.processor.youtube.VideoSiteExtractor
import com.github.sonic.parser.util.VideoSitePattern

/**
 * The Class YoutubeController.
 *
 * @author Nguyen Duc Dung
 * @since 6/19/13 10:47 PM
 *
 */
class VideoSiteController extends Controller {

  val processors = List(new VideoSiteExtractor)

  def isRight(implicit article: Article) = if (article.baseUrl != null && VideoSitePattern.matches(article.doc.baseUri())) true else false

}
