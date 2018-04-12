package nl.codebulb.core

/**
  * @author Robert Lemmens
  *         11-4-18
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

object Currency {
  def apply(s: String)(amount: BigDecimal): Currency = s match {
    case "EUR" => Euro(amount)
    case "USD" => Dollar(amount)
    case "JPY" => JapaneseYen(amount)
  }
}

trait CurrencyImplicits {

  abstract class toCurrency(value: BigDecimal) {
    def euro: Euro = Euro(value)
    def dollar: Dollar = Dollar(value)
    def japaneseYen: JapaneseYen = JapaneseYen(value)
  }

  implicit class IntToCurrency(value: Int) extends toCurrency(value)
  implicit class BigDecimalToCurrency(value: BigDecimal) extends toCurrency(value)
  implicit class LongToCurrency(value: BigDecimal) extends toCurrency(value)
  implicit class StringToCurrency(value: String) extends toCurrency(BigDecimal(value))

}
