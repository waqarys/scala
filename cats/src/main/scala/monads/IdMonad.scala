package monads

import monads.IdMonad.Id

import scala.annotation.tailrec

class IdMonad {
  //type Id[A] = A

  import cats.Functor

  val greet = (name: String) => s"Hell $name"
  val greeting = Functor[Id].map("Waqar")(greet)
  println(greeting)

  import cats.Monad

  implicit val idMonad = new Monad[Id] {
    override def pure[A](x: A): Id[A] = x
    override def flatMap[A, B](fa: Id[A])(f: A => Id[B]): Id[B] = f(fa)
    @tailrec override def tailRecM[A, B](a: A)(f: A => Id[Either[A, B]]): Id[B] = f(a) match {
      case Left(a1) => tailRecM(a1)(f)
      case Right(b) => b
    }
  }

}

object IdMonad extends App {

  type Id[A] = A

  import cats.syntax.flatMap._
  import cats.syntax.functor._
  val id1: Id[Int] = 42
  val id2: Id[Int] = 23

  val resultId = for {
    num1 <- id1
    num2 <- id2
  } yield num1 + num2

  println(resultId)
}
