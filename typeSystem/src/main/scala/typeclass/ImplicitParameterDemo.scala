package typeclass

import java.io.File

object Implicits {
  implicit val current = "."
}

object ImplicitParameterDemo extends App {

  //implicit val current = "."
  import Implicits.current

  def files(implicit directory: String): Either[String, List[File]] = {
    try {
      val file = new File(directory)
      if(file.isDirectory) Right(file.listFiles().toList)
      else Right(List.empty[File])
    } catch {
      case e: Throwable => Left(e.getMessage)
    }
  }

  files.map(listOfFiles => listOfFiles foreach println)
}