import nl.codebulb.core._
import org.scalatest.FlatSpec
import org.scalatest.Matchers._
import nl.codebulb.core.implicits._

class CurrencyParsingTests extends FlatSpec {

  behavior of "parsing a euro"

  it should "parse from all defined types" in {
    10.euro shouldBe a [Euro]
    "10".euro shouldBe a [Euro]
    10L.euro shouldBe a [Euro]
    BigDecimal(10).euro shouldBe a [Euro]
  }

  it should "have the correct parsed 'Amount'" in {
    val euro = Euro(20)
    assert(euro.amount == BigDecimal(20))
    assert(euro.amount != BigDecimal(19.99999999))
  }

  it should "be zero when parsing an empty amount"  in {
    val euro = Euro()
    assert(euro.amount == 0)
  }

  behavior of "parsing a dollar"

  it should "parse from all defined types" in {
    10.dollar shouldBe a [Dollar]
    "10".dollar shouldBe a [Dollar]
    10L.dollar shouldBe a [Dollar]
    BigDecimal(10).dollar shouldBe a [Dollar]
  }

  it should "have the correct parsed 'Amount'" in {
    val dollar = Dollar(25)
    assert(dollar.amount == BigDecimal(25))
    assert(dollar.amount != BigDecimal(19.99999999))
  }

  it should "be zero when parsing an empty amount"  in {
    val dollar = Dollar()
    assert(dollar.amount == 0)
  }

  behavior of "parsing a japanese yen"

  it should "parse from all defined types" in {
    10.japaneseYen shouldBe a [JapaneseYen]
    "10".japaneseYen shouldBe a [JapaneseYen]
    10L.japaneseYen shouldBe a [JapaneseYen]
    BigDecimal(10).japaneseYen shouldBe a [JapaneseYen]
  }

  it should "have the correct parsed 'Amount'" in {
    val yen = JapaneseYen(25)
    assert(yen.amount == BigDecimal(25))
    assert(yen.amount != BigDecimal(19))
  }

  it should "be zero when parsing an empty amount"  in {
    val yen = JapaneseYen()
    assert(yen.amount == 0)
  }

  behavior of "parsing a british pound"

  it should "parse from all defined types" in {
    10.britishPound shouldBe a [BritishPound]
    "10".britishPound shouldBe a [BritishPound]
    10L.britishPound shouldBe a [BritishPound]
    BigDecimal(10).britishPound shouldBe a [BritishPound]
  }

  it should "have the correct parsed 'Amount'" in {
    val pound = BritishPound(25)
    assert(pound.amount == BigDecimal(25))
    assert(pound.amount != BigDecimal(19))
  }

  it should "be zero when parsing an empty amount"  in {
    val pound = BritishPound()
    assert(pound.amount == 0)
  }

  behavior of "parsing a canadian dollar"

  it should "parse from all defined types" in {
    10.canadianDollar shouldBe a [CanadianDollar]
    "10".canadianDollar shouldBe a [CanadianDollar]
    10L.canadianDollar shouldBe a [CanadianDollar]
    BigDecimal(10).canadianDollar shouldBe a [CanadianDollar]
  }

  it should "have the correct parsed 'Amount'" in {
    val dollar = CanadianDollar(30)
    assert(dollar.amount == BigDecimal(30))
    assert(dollar.amount != BigDecimal(10))
  }


  it should "be zero when parsing an empty amount"  in {
    val dollar = CanadianDollar()
    assert(dollar.amount == 0)
  }

  behavior of "parsing a swiss franc"

  it should "parse from all defined types" in {
    10.swissFranc shouldBe a [SwissFranc]
    "10".swissFranc shouldBe a [SwissFranc]
    10L.swissFranc shouldBe a [SwissFranc]
    BigDecimal(10).swissFranc shouldBe a [SwissFranc]
  }

  it should "have the correct parsed 'Amount'" in {
    val franc = SwissFranc(30)
    assert(franc.amount == BigDecimal(30))
    assert(franc.amount != BigDecimal(10))
  }


  it should "be zero when parsing an empty amount"  in {
    val franc = SwissFranc()
    assert(franc.amount == 0)
  }

  behavior of "parsing a australian dollar"

  it should "parse from all defined types" in {
    10.australianDollar shouldBe a [AustralianDollar]
    "10".australianDollar shouldBe a [AustralianDollar]
    10L.australianDollar shouldBe a [AustralianDollar]
    BigDecimal(10).australianDollar shouldBe a [AustralianDollar]
  }

  it should "have the correct parsed 'Amount'" in {
    val dollar = AustralianDollar(30)
    assert(dollar.amount == BigDecimal(30))
    assert(dollar.amount != BigDecimal(10))
  }

  it should "be zero when parsing an empty amount"  in {
    val dollar = AustralianDollar()
    assert(dollar.amount == 0)
  }
}
