package monoids

trait Monoid[A] {
  def compose(a: A, b: A): A
  def empty: A
}

object DefaultMonoids {
  implicit val stringConcatMonoid = new Monoid[String]{
    override def compose(a: String, b: String): String = s"$a$b"
    override def empty: String = ""
  }
}

object Operations {
  def combineAll[A](list: List[A])(implicit monoid: Monoid[A]): A = {
    list.foldRight(monoid.empty)((a, b) => monoid.compose(a, b))
  }
}

object TestMonoid extends App {
  import DefaultMonoids._

  val result = Operations.combineAll(List("a", "b", "c"))
  println(result)
}