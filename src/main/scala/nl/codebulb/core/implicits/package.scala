package nl.codebulb.core

import nl.codebulb.core.important.FunConversions

/**
  * @author Robert Lemmens 
  *         11-4-18
  *
  *   By importing this into the scope you get access to all the needed implicits
  */
package object implicits extends CurrencyImplicits with Operations with FunConversions {

}
