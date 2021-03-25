package monoids

import cats.kernel.Monoid
import cats.implicits.catsKernelStdMonoidForString
import cats.instances.int.catsKernelStdGroupForInt
import cats.instances.map.catsKernelStdMonoidForMap

object CatsMonoid extends App {

  val result = Monoid[String].combineAll(List("a", "b", "cc"))
  println(result)

  val scores = List(Map("Joe" -> 12, "Kate" -> 21), Map("Joe" -> 10))
  val totals = Monoid[Map[String,Int]].combineAll(scores)
  println(totals) // Map(Joe -> 22, Kate -> 21)

}
