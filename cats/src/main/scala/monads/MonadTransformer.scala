package monads

import cats.data.OptionT

import scala.collection.parallel.Task
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object MonadTransformer extends App {

  def generateNum: Future[scala.Option[Long]] = Future {
    val source = Math.round(Math.random * 100)
    if (source <= 90) Some(source) else None
  }

  val maybeNum1F = generateNum
  val maybeNum2F = generateNum

  val resultFO = for {
    num1 <- FutureOption(maybeNum1F)
    num2 <- FutureOption(maybeNum2F)
  } yield num1 + num2

  println(resultFO)

  import cats.instances.future._

  val resultFOT = for {
    num1 <- OptionT(maybeNum1F)
    num2 <- OptionT(maybeNum2F)
  } yield {
    num1 + num2
  }

  println(resultFOT.value)
}

case class FutureOption[A](internal: Future[Option[A]]) {
  def map[B](f: A => B): FutureOption[B] = FutureOption {
    internal.map(_.map(f))
  }

  def flatMap[B](f: A => FutureOption[B]): FutureOption[B] = {
    FutureOption {
      internal.flatMap(maybeValue => {
        maybeValue match {
          case Some(value) => f(value).internal
          case None => Future.successful(None)
        }
      })
    }
  }
}

class GenerationException(number: Long, message: String)
  extends Exception(message)

/*
object NumberProducer {

  import cats.syntax.either._

  def queryNextNumber: Task[Either[GenerationException, Long]] = Task {
    Either.catchOnly[GenerationException] {
      val source = Math.round(Math.random * 100)
      if (source <= 80) source
      else throw new GenerationException(source,
        "The generated number is too big!")
    }
  }
}*/
