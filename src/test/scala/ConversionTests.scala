import nl.codebulb.core._
import org.scalatest.FlatSpec
import org.scalatest.Matchers._
import nl.codebulb.core.implicits._

class ConversionTests extends FlatSpec {

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

  "Converting euro to dollar" should "convert correctly" in {
    val euro = Euro(10)
    euro to Dollar() shouldBe a [Dollar]
    assert((euro to Dollar()).amount == 12.4)
  }

  "Converting dollar to japenese yen" should "convert correctly" in {
    val dollar = Dollar(10)
    dollar to JapaneseYen() shouldBe a [JapaneseYen]
    assert((dollar to JapaneseYen()).amount == 1068.35)
  }

  "Converting japanese yen to dollar" should "convert correctly" in {
    val yen = JapaneseYen(100)
    yen to Dollar() shouldBe a [Dollar]
    assert((yen to Dollar()).amount == 0.936)
  }

  "Converting british pound to canadian dollar" should "convert correctly" in {
    val pound = BritishPound(100)
    pound to CanadianDollar() shouldBe a [CanadianDollar]
    assert((pound to CanadianDollar()).amount == 176)
  }

  "Converting canadian dollar to euro" should "convert correctly" in {
    val dollar = CanadianDollar(100)
    dollar to Euro() shouldBe a [Euro]
    assert((dollar to Euro()).amount == 64)
  }

  "Converting swiss franc to euro" should "convert correctly" in {
    val franc = SwissFranc(100)
    franc to Euro() shouldBe a [Euro]
    assert((franc to Euro()).amount == 86)
  }

  "Converting australian dollar to euro" should "convert correctly" in {
    val dollar = AustralianDollar(100)
    dollar to Euro() shouldBe a [Euro]
    assert((dollar to Euro()).amount == 63)
  }
}
