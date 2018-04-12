package nl.codebulb.core

/**
  * @author Robert Lemmens 
  *         11-4-18
  */
class Converter(conversionMap: Map[(String, String), BigDecimal]) {

  /**
    * Gets the conversion rate from the supplied Map
    *
    * @param from the from currency
    * @param to the to currency
    * @return conversion rate
    */
  def rate(from: Currency, to: Currency): BigDecimal = {
    if(from.getCode() == to.getCode()) 1
    else conversionMap(from.getCode(), to.getCode())
  }

  /**
    * Converts the currency according the conversion rate
    *
    * @param from the from currency
    * @param to the to currency
    * @param converter implicit converter when none is supplied by user. This way conversions between same currency type always work
    * @return
    */
  def convert(from: Currency, to: Currency)(implicit converter: Converter): Currency = {
    Currency{to.getCode()}(rate(from,to) * from.amount)
  }

}

object Converter {
  implicit val converter = new Converter(Map())
}