package typeparameterization

trait AlgebaicOperations {
  type T
  def add(a: T, b: T): T
  def sub(a: T, b: T): T
  def mul(a: T, b: T): T
}

object IntegerOps extends AlgebaicOperations{
  type T = Int

  override def add(a: Int, b: Int): Int = a + b
  override def sub(a: Int, b: Int): Int = a - b
  override def mul(a: Int, b: Int): Int = a * b
}

object AbstractType extends App {

  import IntegerOps._

  println(add(1, 2))
  println(mul(1, 2))
}
