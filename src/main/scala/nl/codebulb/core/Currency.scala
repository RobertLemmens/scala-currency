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

/**
  * Handy apply method so we can create currencies like: Currency{"EUR"}(10) or Currency("EUR")(10) or Currency("eur")(10)
  * s is always converted to uppercase.
  */
object Currency {
  def apply(s: String)(amount: BigDecimal = 0): Currency = s.toUpperCase match {
    case "EUR" => Euro(amount)
    case "USD" => Dollar(amount)
    case "JPY" => JapaneseYen(amount)
  }
}

/**
  * This trait contains the implicit needed to create currencies.
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
  }

  /*
    Below implicits allow us to type for example:
      10.euro
      "10".euro
      10L.euro
     to create Euro(10)
   */
  implicit class IntToCurrency(value: Int) extends toCurrency(value)
  implicit class BigDecimalToCurrency(value: BigDecimal) extends toCurrency(value)
  implicit class LongToCurrency(value: BigDecimal) extends toCurrency(value)
  implicit class StringToCurrency(value: String) extends toCurrency(BigDecimal(value))

}
