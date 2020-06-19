package typeclass

trait Combiner[T] {
  def combine(a: T, b: T): T
}

object Combiner {
  implicit val intCombiner: Combiner[Int] = new Combiner[Int] {
    override def combine(a: Int, b: Int): Int = a + b
  }

  implicit val stringCombiner: Combiner[String] = new Combiner[String] {
    override def combine(a: String, b: String): String = s"$a$b"
  }

  implicit def listCombiner[T]: Combiner[List[T]] = new Combiner[List[T]] {
    override def combine(a: List[T], b: List[T]): List[T] = a ++ b
  }
}

object CombinerOps {
  def combine[T](a: T, b: T)(implicit ev: Combiner[T]): T = ev.combine(a, b)

  implicit class CombinerExt[T](pair: Tuple2[T, T]){
    def combine(implicit ev:Combiner[T]): T = ev.combine(pair._1, pair._2)
  }
}

object TypeClassInstances extends App {

  import typeclass.CombinerOps._

  val combinedIntegers = combine(2, 4)
  println(s"Combined Integer values are: $combinedIntegers")
  println(combine("Well", " Done!"))
  println(combine(List(1, 2), List(3, 4, 5)))

  val combined = (2, 5).combine
  println(combined)
}
