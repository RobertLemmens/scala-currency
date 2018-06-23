import nl.codebulb.core._
import org.scalatest.FlatSpec
import org.scalatest.Matchers._
import nl.codebulb.core.implicits._

import scala.math.BigDecimal.RoundingMode

class OperationTests extends FlatSpec {

  val conversionMap: Map[(String,String), BigDecimal] =
    Map (("EUR", "USD") -> 1.24,
      ("USD", "EUR") -> 0.8082,
      ("EUR", "JPY") -> 132.184,
      ("JPY", "EUR") -> 0.00756,
      ("USD", "JPY") ->  106.835,
      ("JPY", "USD") -> 0.00936,
      ("GBP", "CAD") -> 1.76,
      ("CAD", "EUR") -> 0.64,
      ("CHF", "EUR") -> 0.86,
      ("AUD", "EUR") -> 0.63)

  implicit val converter = new Converter(conversionMap)

  behavior of "Comparing currencies"

  it should "compare valuations correctly" in {
    assert(10.euro !== 10.dollar)
    assert(Euro(10.02168) === Dollar(12.40))
    assert("10".euro > Dollar(10))
    assert("10".euro < CanadianDollar(10))
    assert(Euro(10.02168) >= Dollar(12.40))
    assert(Euro(10.02168) <= Dollar(12.40))
  }

  it should "apply operations correctly" in {
    assert(10.euro / 2 === 5.euro)
    assert(10.euro * 2 === 20.euro)
    assert(10.euro + 2.euro === 12.euro)
    assert(10.euro - 2.euro === 8.euro)
    assert((10.euro ++) === 11.euro)
    assert((10.euro += 2) === 12.euro)
    assert((10.euro += 2) !== 13.euro)
    assert((10.euro --) === 9.euro)
    assert((10.euro --) !== 8.euro)
    assert((10.euro -= 2) === 8.euro)
    assert((10.euro -= 2) !== 7.euro)
  }

  it should "apply operations correctly between currencies" in {
    assert(10.euro + 200.japaneseYen === Euro(11.512))
    assert(10.euro - 2.dollar === Euro(8.3836))

  }
}
