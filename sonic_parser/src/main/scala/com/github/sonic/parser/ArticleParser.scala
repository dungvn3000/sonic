package com.github.sonic.parser

import processor._
import model.Article
import org.jsoup.nodes.Document
import org.apache.commons.lang.StringUtils

/**
 * The Class ArticleParser.
 *
 * @author Nguyen Duc Dung
 * @since 12/23/12 1:45 AM
 *
 */
class ArticleParser {

  val processorsForAutoMode = new Processors <~ List(
    //Step1: Remove hidden element , clean document.
    new DocumentCleaner,
    new LanguageDetector,
    new RemoveHiddenElement,
    new RemoveDirtyElementFilter,
    //Step2: Extract article elements.
    new ArticleExtractor,
    new TitleExtractor,
    //Step3: Try to find potential element.
    new TitleBaseFilter,
    new NumbOfWordFilter,
    new TagBaseFilter,
    new MediaBaseFilter,
    new DistanceBaseFilter,
    //Step4: Remove bad quality element.
    new DirtyImageFilter,
    new HighLinkDensityFilter,
    new CommonElementFilter,
    //Step5: Only keep high score elements.
    new HighestScoreElementFilter,
    new ContainerElementDetector,
    new ExpandTitleToContentFilter,
    //Step 6: Fixed broken link
    new BrokenLinkFixed,
    new StyleCleaner,
    new DuplicateCleaner
  )

  /**
   * Parse a html document to an article
   * @param doc
   * @return
   */
  def parse(doc: Document) = {
    val article = new Article(doc.normalise())
    processorsForAutoMode.process(article)
    article
  }

  /**
   * Parse a html document with special title to an article
   * @param doc
   * @param title
   * @return
   */
  def parse(doc: Document, title: String) = {
    val article = new Article(doc.normalise())
    if (StringUtils.isNotBlank(title)) article.title = title
    processorsForAutoMode.process(article)
    article
  }

}
