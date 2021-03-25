package functors

//Functor is an abstraction for map
trait Functor1[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object SeqF extends Functor1[Seq] {
  def map[A, B](seq: Seq[A])(f: A => B): Seq[B] = seq map f
}

object OptionF extends Functor1[Option] {
  def map[A, B](opt: Option[A])(f: A => B): Option[B] = opt map f
}

object Functor1 extends App {
  val fid: Int => Double = i => 1.5 * i

  println(SeqF.map(Seq(1,2,3,4,5))(fid))

  println(OptionF.map(Some(2))(fid))

  val f1: Int => Int = _ * 2
  val f2: Int => Int = _ + 3
  val f3: Int => Int = _ * 5

  val l = List(1,2,3,4,5)

  //F(f ◦ g) = F(f) ◦ F(g).
  val m12a = SeqF.map(SeqF.map(l)(f1))(f2)
  val m23a = (seq: Seq[Int]) => SeqF.map(SeqF.map(seq)(f2))(f3)
  assert(SeqF.map(m12a)(f3) == m23a(SeqF.map(l)(f1)))
}