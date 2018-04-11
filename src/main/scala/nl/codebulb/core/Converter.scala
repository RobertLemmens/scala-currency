package nl.codebulb.core

/**
  * @author Robert Lemmens 
  *         11-4-18
  */
class Converter(conversionMap: Map[(String, String), BigDecimal]) {

  def rate(from: Currency, to: Currency): BigDecimal = {
    if(from.getCode() == to.getCode()) 1
    else conversionMap(from.getCode(), to.getCode())
  }

  def convert(from: Currency, to: Currency)(implicit converter: Converter): Currency = {
    Currency{to.getCode()}(rate(from,to) * from.amount)
  }

}

object Converter {
  implicit val converter = new Converter(Map())
}