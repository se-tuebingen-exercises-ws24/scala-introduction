package demo

// In general, I can recommend the Scala 3 book:
//   https://docs.scala-lang.org/scala3/book/introduction.html
//
// Since in Info 2, you have learnt Java this guide might be particularly helpful:
//   https://docs.scala-lang.org/tutorials/scala-for-java-programmers.html

// Collection API documentation
//   https://scala-lang.org/api/3.2.0/scala/collection/immutable/List.html
//
// Short intro into collections:
//   https://docs.scala-lang.org/scala3/book/collections-classes.html
//
// Complexity of collections:
//   https://docs.scala-lang.org/overviews/collections-2.13/performance-characteristics.html

// Imports are very similar to the one's of Java.
import scala.collection.immutable.List

// Primitives
// ----------
// We can use `sbt> console` to experiment with different expressions in the context
// of this project.
//
// For example:
//     sbt> console
//     scala> demo.demoPrimitives
// will evaluate everything within the object `demoPrimitives`. In Scala, we not only use objects to model our
// application, but also for namespacing.
//
// We can also use imports:
//     scala> import demo.demoPrimitives.*
//     scala> myString
//     val res1: String = helloworld
object demoPrimitives {

  // Numbers
  1 + 1

  // Strings
  val myString = "hello" + "world"
  s"Hello ${myString}!"

  // Booleans
  1 < 3
  true
  false
  !true

  // Unit -- is used whenever in Java you would use void
  () : Unit
}

// Bindings
object demoBindings {

  // immutable / constant bindings
  //
  // In BSL, this would be
  //   (define x 1)
  val x = 1

  // mutable / variable bindings
  var y = 2
  // can be reassigned:
  y = 3

  // definitions (like a function without arguments)
  // will be evaluated everytime it is referenced
  def sayHello = println("hello")
  sayHello
  sayHello
}

// Functions
object demoFunctions {

  // Simple functions
  // In BSL, this would be
  //
  //   (define (sayHelloTo name) ...)
  def sayHelloTo(name: String) = println("hello " + name)

  // if the function body is more than one expression, we can either
  // wrap it into braces:
  def sayHelloTwice(name: String) = {
    println("hello " + name)
    println("hello again " + name)
  }

  // or use Python-like indentation-based syntax:
  def sayHelloThrice(name: String) =
    println("hello " + name)
    println("hello again " + name)
    println("and hello again " + name)

  // Multiple arguments
  def add(n: Int, m: Int): Int = n + m
}

object demoControlflow {

  // conditionals are expressions like in BSL (unlike in Java)...
  val result = if (10 > 14) "hello" else "world"

  var n = 10;
  while (n > 0) {
    println(n);
    n = n - 1
  }

  // for-comprehensions
  //   https://docs.scala-lang.org/tour/for-comprehensions.html
  for (m <- 0 until 10) {
    println(m)
  }

  def cartesian(size: Int) =
    for (n <- 0 until size; m <- 0 until size) {
      println(s"${n}, ${m}")
    }

  def triples(max: Int, sum: Int) =
    for {
      i <- 1 until max
      j <- (i + 1) until max
      k <- (j + 1) until max
      if i + j + k == sum // this is called a "guard" expression
    } println(s"($i, $j, $k)")

}

object demoArrays {

  // Arrays
  val arr = Array.ofDim[String](10)
  arr(0) = "hello"
  arr(1) = "world"

  println(arr(0))

  // Mutable Maps (sometimes called "dictionaries")
  import scala.collection.mutable.HashMap
  val m: HashMap[String, Int] = HashMap.empty
  m.put("mykey", 17)
  m.put("myother", 99)
  println(m.getOrElse("mykey", -1))

}

