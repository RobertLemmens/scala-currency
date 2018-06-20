package nl.codebulb.core.important

import nl.codebulb.core.Currency

import scala.math.BigDecimal

/**
  * @author Robert Lemmens 
  *         12-4-18
  */
trait FunConversions {

  implicit class LocalizedStringConversions(c: Currency) {

    def japanese(): String = {
      val topNumLength = c.amount.toString.length
      val topNumString = c.amount.toString
      val head = if(topNumLength > 5) topNumString.dropRight(4) else ""
      val tail = if(topNumString.takeRight(5).startsWith("0")) "1" + topNumString.takeRight(4) else topNumString.takeRight(5) //10000

      def decomposeNumber(number: BigDecimal, word: String): String = {
        val numString = number.toString
        val numLength = numString.length
        val firstChar = if (number != 0) japeneseDecimals((numString.charAt(0) - '0') - 1) else ""
        val powerChar = if (numLength > 1) japaneseScales(numLength - 2) else ""
        val newWord = word + firstChar + powerChar
        if (numLength == 1) newWord else decomposeNumber(BigDecimal(numString.substring(1)), newWord)
      }

      if((topNumLength < 5 && topNumLength > 1) && c.amount.toString().substring(0,1).equals("1"))  // cut the 1 character when its less then 10000
        decomposeNumber(c.amount, "").substring(1)
      else if (topNumLength == 1 || topNumLength == 5)
        decomposeNumber(c.amount, "")
      else {
        val first = decomposeNumber(BigDecimal(head), "").substring(1)
        val second = decomposeNumber(BigDecimal(tail), "").substring(1)
        first + second
      }
    }
  }

  private val japeneseDecimals = Array("一", "二", "三", "四", "五", "六", "七", "八", "九")
  private val japaneseScales = Array("十", "百", "千", "万", "億") // 10, 100, 1000, 10,000, 100,000,000

}
