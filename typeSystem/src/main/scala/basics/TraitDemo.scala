package basics

import java.io.File

import scala.io.Source

object TraitDemo extends App {

  trait FileOps {
    self: File =>

    def isTextFile: Boolean = this.getName.endsWith("txt")

    def readText: Iterator[String] = if(this.isTextFile) Source.fromFile(this).getLines() else Iterator.empty

    def printLines: Unit = this.readText foreach println
  }

  val fooTextFile = new File("C:\\working\\study-material\\getting-started-ansible\\course-materials-readme.txt") with FileOps

  println(fooTextFile.getName)
  println(fooTextFile.isTextFile)
  fooTextFile.printLines
}
