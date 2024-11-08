package demo

import scala.io.StdIn.readLine

import spray.json.*
import DefaultJsonProtocol.*

@main
def sayHello = {
  println("Hey, what's your name???? (please press <Enter> after entering your name)")

  // Javascript object notation (JSON)
  // "{ \"name\": \"jonathan\" }"

  val input = readLine()
  val jsonObject = JsonParser(ParserInput(input)).asJsObject
  val name = jsonObject.fields("name").toString

  val greeting =
    s"""Hello there, ${name}!
      |
      |Seems like you have all dependencies set up correctly.
      |Apparently, this application is running on ${os} with JDK ${jdkVersion}.
      |""".stripMargin

  println(greeting)
}


def jdkVersion = System.getProperty("java.version")

def os = System.getProperty("os.name")
