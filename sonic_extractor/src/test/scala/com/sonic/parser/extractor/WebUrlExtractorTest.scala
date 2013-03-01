package com.sonic.parser.extractor

import org.scalatest.FunSuite
import com.github.sonic.parser.extractor.DefaultExtractor
import com.github.sonic.parser.extractor.rule.WebUrlRule
import org.expecty.Expecty

/**
 * The Class WebUrlExtractorTest.
 *
 * @author Nguyen Duc Dung
 * @since 3/1/13 10:37 AM
 *
 */
class WebUrlExtractorTest extends FunSuite {

  val url1 = " http://vnexpress.net/gl/doi-song/2011/08/lang-phong-bi-tay-chay-khi-vao-dat-lien/  xem ne ba con"
  val url2 = " https://www.google.com.vn/search?sourceid=chrome&ie=UTF-8&q=http+protocol dkm vcl vlbc"
  val url3 = ""
  val url4 = "         "
  val url5 = "      https://   http://  https://  "
  val url6 = "      sadsad://   sdasd://  sadsd://  "
  val url7 = "http://vnexpress.net"
  val url8 = "http://www.h2shop.vn/ipad/ipad-4/may-ipad-4/ chỗ này bán rẻ nhất nè con"

  val webUrlExtractor = new DefaultExtractor(new WebUrlRule)
  val expect = new Expecty()

  test("extract web url form a string") {
    webUrlExtractor.extract(url1)
    expect {
      webUrlExtractor.results.size == 1
      webUrlExtractor.results(0) == "http://vnexpress.net/gl/doi-song/2011/08/lang-phong-bi-tay-chay-khi-vao-dat-lien/"
    }

    webUrlExtractor.results.clear()
    webUrlExtractor.extract(url2)
    expect {
      webUrlExtractor.results.size == 1
      webUrlExtractor.results(0) == "https://www.google.com.vn/search?sourceid=chrome&ie=UTF-8&q=http+protocol"
    }

    webUrlExtractor.results.clear()
    webUrlExtractor.extract(url3)
    expect {
      webUrlExtractor.results.size == 0
    }

    webUrlExtractor.results.clear()
    webUrlExtractor.extract(url4)
    expect {
      webUrlExtractor.results.size == 0
    }

    webUrlExtractor.results.clear()
    webUrlExtractor.extract(url5)
    expect {
      webUrlExtractor.results.size == 0
    }

    webUrlExtractor.results.clear()
    webUrlExtractor.extract(url6)
    expect {
      webUrlExtractor.results.size == 0
    }

    webUrlExtractor.results.clear()
    webUrlExtractor.extract(url7)
    expect {
      webUrlExtractor.results.size == 1
      webUrlExtractor.results(0) == "http://vnexpress.net"
    }

    webUrlExtractor.results.clear()
    webUrlExtractor.extract(url8)
    expect {
      webUrlExtractor.results.size == 1
      webUrlExtractor.results(0) == "http://www.h2shop.vn/ipad/ipad-4/may-ipad-4/"
    }

  }

}
