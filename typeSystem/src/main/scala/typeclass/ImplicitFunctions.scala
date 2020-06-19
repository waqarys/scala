package typeclass

import java.io.File

import scala.io.Source

object ImplicitFunctions extends App {

  type TextLine = String

  def parse(text: Iterator[TextLine]): List[String] = {
    text.map{line =>
      line
        .split(" ")
        .filter(_.length > 1)
        .map(_.toLowerCase)
        .toList
    }.toList.flatten
  }

  val file = new File("./build.sbt")

  //val linesOfString: Iterator[String] = Source.fromFile(file).getLines()

  implicit def strToTextLineIterator(str: String): Iterator[String] = Iterator(str)
  val lineOfString = "This is a line of string."

  parse(lineOfString) foreach println
}
