package com.github.sonic.parser.controller

import com.github.sonic.parser.processor._
import com.github.sonic.parser.model.Article

/**
 * The Class AutoModeProcessors.
 *
 * @author Nguyen Duc Dung
 * @since 6/19/13 10:42 PM
 *
 */
class AutoModeController extends Controller {

  val processors = List(
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

  //This is default processor list using in most of case
  def isRight(implicit article: Article) = true
}
