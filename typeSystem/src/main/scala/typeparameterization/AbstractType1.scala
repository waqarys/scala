package typeparameterization

trait OperationGenerator[In]{
  type Out

  def operation: Out
}

object AbstractType1 extends App {
  type OperationInputType = String

  val capitalizer = new OperationGenerator[OperationInputType] {
    override type Out = OperationInputType => String

    override def operation: Out = (str: OperationInputType) => str.capitalize
  }

  val listOfString = List("first", "Second", "Third", "fourth")
  listOfString.map(capitalizer.operation) foreach println
}
