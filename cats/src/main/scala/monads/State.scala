package monads

object State extends App {

  def getIntXorShift(seed: Int): Int = {
    var x = seed
    x ^= (x << 21)
    x ^= (x >>> 35)
    x ^= (x << 4)
    x
  }

  def genChar(seed: Int): (Int, Char) = {
    val newSeed = getIntXorShift(seed)
    val number = Math.abs(newSeed % 25) + 65
    (newSeed, number.toChar)
  }

  import cats.data.State
  val nextCharS = cats.data.State[Int, Char]{seed =>
    genChar(seed)
  }

  val initialSeed = 42
  val randomS = for {
    first <- nextCharS
    second <- nextCharS
    third <- nextCharS
  } yield List(first, second, third)

  val result = randomS.runA(initialSeed)
  println(result.value.mkString)

  val nextCharSt = cats.data.StateT[cats.Eval, Int, Char]{seed =>
    cats.Eval.now(genChar(seed))
  }

  val randomSt = for {
    first <- nextCharSt
    second <- nextCharSt
    third <- nextCharSt
  } yield List(first, second, third)

  val resultT = randomSt.runA(initialSeed)
  println(resultT.value.mkString)
}
