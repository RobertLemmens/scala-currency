package nl.codebulb.core
import java.util.Comparator

/**
  * @author Robert Lemmens 
  *         11-4-18
  */
trait operations {

  implicit class CurrencyOperators(c1: Currency)(implicit converter: Converter)  {
    def +(c2: Currency): Currency = performOperation(c1, c2, _ + _, converter)
    def -(c2: Currency): Currency = performOperation(c1, c2, _ - _, converter)
    def *(c2: BigDecimal): Currency = Currency{c1.getCode()}(c1.amount * c2)
    def /(c2: Currency): Currency = performOperation(c1, c2, _ / _, converter)
    def /(c2: BigDecimal): Currency = Currency{c1.getCode()}(c1.amount / c2)
    def to(c2: Currency): Currency = Currency{c2.getCode()}(converter.convert(c1,c2).amount)
    def ===(c2: Currency): Boolean = compare(c1,c2, converter) == 0
    def !==(c2: Currency): Boolean = compare(c1,c2, converter) != 0
  }

  private def compare(c1: Currency, c2: Currency, converter: Converter): Int = {
    val convertedCurrency = converter.convert(c2, c1)
    c1.amount compare convertedCurrency.amount
  }

  private def performOperation(c1: Currency, c2: Currency, operation: (BigDecimal, BigDecimal)=> BigDecimal, converter: Converter): Currency = {
    val convertedCurrency = converter.convert(c2, c1)
    Currency{c1.getCode()}(operation(c1.amount, convertedCurrency.amount))
  }

}
