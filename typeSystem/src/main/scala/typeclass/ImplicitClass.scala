package typeclass

import java.io.File

import scala.io.Source

object ImplicitClass extends App {

  object Implicits{
    implicit class FileExt(file: File){
      def isTextFile: Boolean = file.getName.endsWith("txt") || file.getName.endsWith("sbt")
      def readText: Iterator[String] = if(this.isTextFile) Source.fromFile(file).getLines() else Iterator.empty
      def printLines: Unit = file.readText foreach println
    }
  }

  import Implicits._

  val sbtFile = new File("./build.sbt")

  val sbtFileName = sbtFile.getName
  println(s"File Name is: ${sbtFileName}")

  val isSbtTextFile = sbtFile.isTextFile
  println(s"Is sbt a text file: ${isSbtTextFile}")

  sbtFile.printLines
}
