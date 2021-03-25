package functors

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

case class Cell[A](value: A) {
  def map[B](function: A => B): Cell[B] = {
    Cell(function(value))
  }
}

import cats.Functor
object Test extends App {
  implicit val cellFunctor: Functor[Cell] = new Functor[Cell] {
    override def map[A, B](fa: Cell[A])(f: A => B): Cell[B] = fa.map(f)
  }

  import cats.implicits.catsStdInstancesForOption
  val maybeName = Option("Waqar")
  println(Functor[Option].map(maybeName)(_.length))

  def greet(name: String): String = s"Hello $name!"
  println(Functor[Option].lift(greet)(maybeName))
}