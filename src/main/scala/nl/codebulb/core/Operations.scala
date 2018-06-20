package nl.codebulb.core
import java.util.Comparator

import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

/**
  * @author Robert Lemmens 
  *         11-4-18
  */
trait Operations {

  /**
    * Implicits operations for all currencies
    *
    * @param c1 currency thats "operated upon"
    * @param converter the currency converter, implicitly provided.
    */
  implicit class CurrencyOperators(c1: Currency)(implicit converter: Converter)  {

    /**
      * Adds two currencies
      * @param c2
      * @return
      */
    def +(c2: Currency): Currency = performOperation(c1, c2, _ + _, converter)

    /**
      * Substracts one currency from the other
      *
      * @param c2
      * @return
      */
    def -(c2: Currency): Currency = performOperation(c1, c2, _ - _, converter)

    /**
      * Multiplies a currency with a number. You cant multiply currencies with each other as that makes no sense
      * @param c2
      * @return
      */
    def *(c2: BigDecimal): Currency = Currency(c1.getCode())(c1.amount * c2)

    /**
      * Divide one currency by the other
      * @param c2
      * @return
      */
    def /(c2: Currency): Currency = performOperation(c1, c2, _ / _, converter)

    /**
      * Divide a currency by a number
      * @param c2
      * @return
      */
    def /(c2: BigDecimal): Currency = Currency(c1.getCode())(c1.amount / c2)

    /**
      * Convert one currency to the other
      * @param c2
      * @return
      */
    def to(c2: Currency): Currency = Currency(c2.getCode())(converter.convert(c1,c2).amount)

    /**
      * Rounds the currency to the supplied decimals
      *
      * @param decimals amount of allowed decimal places
      * @param roundingMode the roundingmode (from BigDecimal)
      * @return
      */
    def round(decimals: Int, roundingMode: RoundingMode): Currency = Currency(c1.getCode())(c1.amount.setScale(decimals, roundingMode))

    /**
      * Cuts all decimal places.
      *
      * @return
      */
    def truncateToWhole: Currency = Currency(c1.getCode())(c1.amount.setScale(0, RoundingMode.FLOOR))
  }

  implicit class RelationalOperators(c1: Currency)(implicit converter: Converter) {
    /**
      * Compare two currencies by first converting them to the same
      * @param c2
      * @return true if c1 == c2
      */
    def ===(c2: Currency): Boolean = compare(c1,c2, converter) == 0

    /**
      * Compare two currencies by first converting them to the same
      * @param c2
      * @return true if c1 != c2
      */
    def !==(c2: Currency): Boolean = compare(c1,c2, converter) != 0

    /**
      * Check if currency 1 is greater than currency 2
      *
      * @param c2
      * @return true if c1 > c2
      */
    def >(c2: Currency): Boolean = gt(c1, c2, converter)

    /**
      * Check if currency 1 is less than currency 2
      *
      * @param c2
      * @return true if c1 < c2
      */
    def <(c2: Currency): Boolean = lt(c1, c2, converter)

    /**
      * Check if currency 1 is greater than or equal
      *
      * @param c2
      * @return
      */
    def >=(c2: Currency): Boolean = c1 > c2 || c1 === c2

    /**
      * Check if currency 1 is less than or equal
      * @param c2
      * @return
      */
    def <=(c2: Currency): Boolean = c1 < c2 || c1 === c2
  }

  implicit class AssignmentOperators(c1: Currency)(implicit converter: Converter) {

    /**
      * Increments the currency amount with 1
      *
      * @return
      */
    def ++(): Currency = Currency(c1.getCode())(c1.amount + 1)

    /**
      * Increments the currency amount with x
      *
      * @param x
      * @return
      */
    def +=(x: BigDecimal): Currency = Currency(c1.getCode())(c1.amount + x)

    /**
      * Decrements the currency amount with 1
      *
      * @return
      */
    def --(): Currency = Currency(c1.getCode())(c1.amount - 1)

    /**
      * Decrements the currency amount with x
      *
      * @param x
      * @return
      */
    def -=(x: BigDecimal): Currency = Currency(c1.getCode())(c1.amount - x)

  }

  /**
    * Helper def to compare two currencies
    *
    * @param c1 currency one
    * @param c2 currency two
    * @param converter the currency converter
    * @return
    */
  private def compare(c1: Currency, c2: Currency, converter: Converter): Int = {
    val convertedCurrency = converter.convert(c2, c1)
    c1.amount compare convertedCurrency.amount
  }

  /**
    * Helper to check for greather than
    *
    * @param c1
    * @param c2
    * @param converter
    * @return true if c1 > c2
    */
  private def gt(c1: Currency, c2: Currency, converter: Converter): Boolean = {
    val convertedCurrency = converter.convert(c2, c1)
    c1.amount > convertedCurrency.amount
  }

  /**
    * Helper to check for less than
    *
    * @param c1
    * @param c2
    * @param converter
    * @return
    */
  private def lt(c1: Currency, c2: Currency, converter: Converter): Boolean = {
    val convertedCurrency = converter.convert(c2, c1)
    c1.amount > convertedCurrency.amount
  }

  /**
    * Helper def to perform some mathematical operation on currencies by converting to a common currency first.
    *
    * @param c1 currency one
    * @param c2 currency two
    * @param operation the mathmatical operation
    * @param converter the currency converter
    * @return
    */
  private def performOperation(c1: Currency, c2: Currency, operation: (BigDecimal, BigDecimal)=> BigDecimal, converter: Converter): Currency = {
    val convertedCurrency = converter.convert(c2, c1)
    Currency(c1.getCode())(operation(c1.amount, convertedCurrency.amount))
  }

}
