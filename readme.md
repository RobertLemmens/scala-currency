# A simple DSL for currencies
This is an experimental library which aim is to create a DSL where currencies are 
represented using case classes. Most currency libraries function around a common field that holds some currency 
information. I want the result to be Euro(10) or Dollar(20) instead, and still do useful operations on them without too much boilerplate.

## Usage

```scala
import nl.codebulb.core.implicits._

10.euro

```
Will give you:
scala> res0: nl.codebul.core.Euro = Euro(10)

A nice Euro(10) object, simple to read and use. 


Lets add a conversion table for some currencies and do some operations now.
You need an implicit Converter for the conversions to work. You can add the rates easily like so:

```scala
import nl.codebulb.core.implicits._
val conversionMap: Map[(String,String), BigDecimal] = 
    Map (("EUR", "USD") -> 1.24,
         ("USD", "EUR") -> 0.8082,
         ("EUR", "JPY") -> 132.184,
         ("JPY", "EUR") -> 0.00756,
         ("USD", "JPY") ->  106.835,
         ("JPY", "USD") -> 0.00936)
         
implicit val converter = new Converter(conversionMap)
```
__________

Now we have that in scope, lets do some real work
```scala
10.euro + 2.dollar // Euro(11.6164)

Euro(10) + Dollar(2) // Euro(11.6164)

10.euro + 20.dollar to JapaneseYen() // JapaneseYen(3458.4621760)

Euro(10) * 2 // Euro(20)

Dollar(10) / 2 // Dollar(5)

Dollar(10) / Euro(2) // Dollar(4.03225...)
```

As you can see its very easy to use and reason about.

## Bugs and feedback
The goal of this project is to keep the currencies as case classes so we can represent them as Euro(10) etc while being as functional as possible without too much boilerplate. Its all about the looks! If you have some good suggestions please let me know, im especially curious as to how to implement a somewhat generic Numeric[Currency] so lists behave as expected.