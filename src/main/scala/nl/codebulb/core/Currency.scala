package nl.codebulb.core

/**
  * @author Robert Lemmens
  *         11-4-18
  *
  *         All currencies extend from base class Currency. This is how we make the implicits work for all currencies.
  *         All default amounts are 0 so you can use the 'to' function without typing a random number in there.
  */
trait Currency {
  val amount: BigDecimal
  def getCode(): String
}

case class Euro(amount: BigDecimal = 0) extends Currency {
  override def getCode(): String = "EUR"
}

case class Dollar(amount: BigDecimal = 0) extends Currency {
  override def getCode(): String = "USD"
}

case class JapaneseYen(amount: BigDecimal = 0) extends Currency {
  override def getCode(): String = "JPY"
}

case class BritishPound(amount: BigDecimal = 0) extends Currency {
  override def getCode(): String = "GBP"
}

case class CanadianDollar(amount: BigDecimal = 0) extends Currency {
  override def getCode(): String = "CAD"
}

case class SwissFranc(amount: BigDecimal = 0) extends Currency {
  override def getCode(): String = "CHF"
}

case class AustralianDollar(amount: BigDecimal = 0) extends Currency {
  override def getCode(): String = "AUD"
}

/**
  * Handy apply method so we can create currencies like: Currency{"EUR"}(10) or Currency("EUR")(10) or Currency("eur")(10)
  * s is always converted to uppercase.
  */
object Currency {
  def apply(s: String)(amount: BigDecimal = 0): Currency = s.toUpperCase match {
    case "EUR" => Euro(amount)
    case "USD" => Dollar(amount)
    case "JPY" => JapaneseYen(amount)
    case "GBP" => BritishPound(amount)
    case "CAD" => CanadianDollar(amount)
    case "CHF" => SwissFranc(amount)
    case "AUD" => AustralianDollar(amount)
  }
}

/**
  * This trait contains the implicit needed to create currencies from different datatypes.
  */
trait CurrencyImplicits {

  /**
    * Converter from BigDecimal to concrete Currency
    * @param value
    */
  abstract class toCurrency(value: BigDecimal) {
    def euro: Euro = Euro(value)
    def dollar: Dollar = Dollar(value)
    def japaneseYen: JapaneseYen = JapaneseYen(value)
    def britishPound: BritishPound = BritishPound(value)
    def canadianDollar: CanadianDollar = CanadianDollar(value)
    def swissFranc: SwissFranc = SwissFranc(value)
    def australianDollar: AustralianDollar = AustralianDollar(value)
  }

  /*
    Below implicits allow us to type for example:
      10.euro
      "10".euro
      10L.euro

     == Euro(10)
   */
  implicit class IntToCurrency(value: Int) extends toCurrency(value)
  implicit class BigDecimalToCurrency(value: BigDecimal) extends toCurrency(value)
  implicit class LongToCurrency(value: Long) extends toCurrency(value)
  implicit class StringToCurrency(value: String) extends toCurrency(BigDecimal(value))


}
