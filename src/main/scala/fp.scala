package demo


// Scala supports first-class and higher-order functions as known
// from ISL in Info 1.
object demoHigherOrderFunctions {

  // higher-order functions
  def compose(f: Int => Int, g: Int => Int)(n: Int): Int = g(f(n))

  // first-class functions
  compose(x => x + 1, y => y * 2)(42) // 86

  // **single argument** sections can also be invoked with braces!
  compose(x => x + 1, y => y * 2) {
    42
  }

  // this is particularly useful for functions
  def runWithSomeValues(fun: Int => Int) = fun(1) + fun(2)

  runWithSomeValues { n =>
    println(s"I have been called with ${ n }")
    n * 2
  }
}

// By default many collections in Scala are *immutable*.
object demoImmutable {

  // Lists
  // -----
  val l = List(1, 2, 3)
  def sum(l: List[Int]): Int = l match {
    case Nil => 0
    case head :: tail => head + sum(tail)
  }

  sum(l)

  // important library functions
  l.map { x => x > 2 } // List(false, false, true)
  l.filter { x => x > 2 } // List(2)
  l.collect { case x if x > 2 => x * 2 } // List(6)

  l.map { x => List(x) } // List(List(1), List(2), List(3))
  l.map { x => List(x) }.flatten // List(1, 2, 3)
  l.flatMap { x => List(x) } // List(1, 2, 3)

  for (element <- l) {
    println(element)
  }
  // ===
  l.foreach { element => println(element) }

  for (element <- l)
    yield element > 2
  // ===
  l.map { x => x > 2 }


  // Sets
  // ----
  // there is many more collections, like maps and sets...
  val mySet = Set(0, 0, 1, 2, 3).intersect(Set(1, 3, 4)) // Set(1, 3)


  // Option
  // ------
  val maybeFirst = l.find { x => x > 2 }
  maybeFirst match {
    case Some(value) => println(s"Value found ${ value }")
    case None => println("No value found")
  }

  def saveDiv(n: Int, m: Int): Option[Int] =
    if (m == 0) None
    else Some(n / m)
}

object demoRecords {

  // Case classes / structs
  //
  // In BSL, this would be
  //   (define-struct person (name favoriteFood))
  case class Person(name: String, favoriteFood: String)

  // here p.name is like (person-name p) in BSL
  val p = Person("Jonathan", "Burgers")
  println(s"Hello ${p.name}. I see you like ${p.favoriteFood}")

  // also like in Racket, we can use pattern matching to extract information
  // from records / structs / case-classes.
  p match {
    case Person(name, food) => println("Hello" + name)
  }
}


// In Info 1 you have learnt about sumtypes (also sometimes called "union types").
// For example:
//
// A traffic light is one of:
// - red
// - yellow
// - green

// In Scala, we have multiple ways of modeling this.
// 1) As an enumeration (e.g., enum ... { case A; case B })
// 2) By using type union (e.g., A | B)
// 3) By using subtyping.
//
// Here we show the first:
object demoEnumeration {

  // 1) Enumeration
  enum TrafficLight {
    case Red
    case Yellow
    case Green
  }

  def switchTrafficLight(t: TrafficLight): TrafficLight = t match {
    case TrafficLight.Red => TrafficLight.Green
    case TrafficLight.Yellow => TrafficLight.Red
    case TrafficLight.Green => TrafficLight.Yellow
  }

  println(switchTrafficLight(TrafficLight.Green)) // Yellow
}

// More on Pattern Matching
// ------------------------
// Pattern matching in Scala is a very important feature.
// It replaces instanceof checks from other languages like Java.

object demoPatternmatching {

  import demoEnumeration.*

  // This allows us to use `Green` etc. without the prefix `TrafficLight.`:
  import TrafficLight.*

  case class Crossing(horizontal: TrafficLight, vertical: TrafficLight)

  def isAllowedConfig(c: Crossing): Boolean = c match {
    case Crossing(Green, Green) => false
    case Crossing(Yellow, Green) => false
    case Crossing(Green, Yellow) => false

    // this is called
    case _ => true
  }

  println(isAllowedConfig(Crossing(Yellow, Green))) // false
  println(isAllowedConfig(Crossing(Yellow, Red))) // true

  // we can also pattern match on something, but also remember the original value:
  def isAllowedConfig2(c: Crossing): Boolean = c match {
    case Crossing(c1 @ Green, c2 @ Green) => println(s"Is allowed: ${c1} and ${c2}"); false
    case Crossing(Yellow, Green) => false
    case Crossing(Green, Yellow) => false

    // this is called
    case _ => true
  }
}

object demoAlgebraicDatatypes {

  // Individual variants of a sumtype in Scala can be records again.
  // For example, optional values

  // We can translate the following data type definition of BSL
  //
  //      (define-struct posn (x y))
  //      ; A Posn is a structure: (make-posn Number Number)
  //      ; interp. the number of pixels from left and from top
  //
  //      (define-struct gcircle (center radius))
  //      ; A GCircle is (make-gcircle Posn Number)
  //      ; interp. the geometrical representation of a circle
  //
  //      (define-struct grectangle (corner-ul corner-dr))
  //      ; A GRrectangle is (make-grectangle Posn Posn)
  //      ; interp. the geometrical representation of a rectangle
  //      ; where corner-ul is the upper left corner
  //      ; and corner-dr the down right corner
  //
  //      ; A Shape is either:
  //      ; - a GCircle
  //      ; - a GRectangle
  //      ; interp. a geometrical shape representing a circle or a rectangle
  //
  //
  // to Scala as follows:

  /**
   * The number of pixels from left and from top
   */
  case class Posn(x: Int, y: Int)

  /**
   * A geometrical shape representing a circle or a rectangle
   */
  enum Shape {
    /**
     * The geometrical representation of a circle
     */
    case Circle(center: Posn, radius: Int)

    /**
     * The geometrical representation of a rectangle
     */
    case Rectangle(upperLeft: Posn, lowerRight: Posn)
  }

  val example = Shape.Rectangle(Posn(10, 20), Posn(15, 25))

  // Again, we can use pattern matching to consume the data

  def move(posn: Posn, by: Posn): Posn = (posn, by) match {
    case (Posn(x, y), Posn(dx, dy)) => Posn(x + dx, y + dy)
  }

  def move(shape: Shape, by: Posn): Shape = shape match {
    case Shape.Circle(center, radius) =>
      Shape.Circle(move(center, by), radius)
    case Shape.Rectangle(upperLeft, lowerRight) =>
      Shape.Rectangle(move(upperLeft, by), move(lowerRight, by))
  }

  println(move(example, Posn(3, 4)))

}


object recursiveData {

  // Like in Info 1, data type definitions can be recursive.
  //
  // Let us translate the following example of Info 1.
  //
  //    (define-struct person (name father mother))
  //
  //    ; A FamilyTree is either:
  //    ; - (make-person String FamilyTree FamilyTree)
  //    ; - #false
  //    ; interp. either the name of a person and the tree of its parents,
  //    ; or #false if the person is not known/relevant.

  enum FamilyTree {
    case Unknown
    case Person(name: String, father: FamilyTree, mother: FamilyTree)
  }

  // We can also translate the following example
  //  (define Bob
  //    (make-person "Bob"
  //      (make-person "Alice" #false #false)
  //      (make-person "Horst"
  //        (make-person "Joe"
  //          #false
  //          (make-person "Rita"
  //            #false
  //            #false))
  //          #false)))

  import FamilyTree.*

  val Bob = Person("Bob",
    Person("Alice", Unknown, Unknown),
    Person("Horst",
      Person("Joe", Unknown, Person("Rita", Unknown, Unknown)),
      Unknown))

  // We can again follow the design recipe to implement the function `person-has-ancestor`,
  // here "hasAncestor"

  def hasAncestor(person: FamilyTree, ancestor: String): Boolean =
    person match {
      case FamilyTree.Unknown => false
      case FamilyTree.Person(name, father, mother) =>
        if (name == ancestor) then true
        else hasAncestor(father, ancestor) || hasAncestor(mother, ancestor)
    }

  println(hasAncestor(Bob, "Joe"))

  // Pattern matching in Scala also supports "guards" and we can write the function slightly
  // differently.

  def hasAncestor2(person: FamilyTree, ancestor: String): Boolean =
    person match {
      case FamilyTree.Unknown => false
      case FamilyTree.Person(name, father, mother) if name == ancestor => true
      case FamilyTree.Person(name, father, mother) =>
        hasAncestor2(father, ancestor) || hasAncestor2(mother, ancestor)
    }
}
