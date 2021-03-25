package monads

//Monad is an abstraction for flatMap
trait Monad1[M[_]] {
  def flatMap[A, B](fa: M[A])(f: A => M[B]): M[B]
  def unit[A](a: => A): M[A]
}

object SeqM extends Monad1[Seq] {
  override def flatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] = seq flatMap f

  override def unit[A](a: => A): Seq[A] = Seq(a)
}

object OptionM extends Monad1[Option]{
  override def flatMap[A, B](opt: Option[A])(f: A => Option[B]): Option[B] = opt flatMap f

  override def unit[A](a: => A): Option[A] = Option(a)
}

object Monad1 extends App {
  val f1: Int => Seq[Int] = i => 0 until 10 by ((math.abs(i) % 10) + 1)
  val unitInt: Int => Seq[Int] = (i:Int) => SeqM.unit(i)
  val f2: Int => Seq[Int] = i => Seq(i+1)

  val seq: Seq[Int] = Seq(1)
  assert(SeqM.flatMap(SeqM.unit(1))(f1)  == f1(1))
  assert(SeqM.flatMap(seq)(unitInt) == seq)
}