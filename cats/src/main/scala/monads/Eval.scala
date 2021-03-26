package monads

import cats.Eval

object Eval extends App {

  var counter = 0
  def increment(): Int ={counter += 1; counter}

  val now = cats.Eval.now(increment()) //always returns 1
  println(now.value)
  println(now.value)

  val later = cats.Eval.later(increment())// subsequesnt calls returns memoized values
  println(later.value)
  println(later.value)

  val always = cats.Eval.always(increment()) //memoization is not used
  println(always.value)
  println(always.value)

}
