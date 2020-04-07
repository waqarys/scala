/*
class NetWorthSpec extends App {

  def runTests(): Unit = {
    assert(NetWorth.calculate(100, 20) == 80, "the result should be 80")
    assert(NetWorth.calculate(1000, 2000) == -1000, "the result should be -1000")
  }

  override def main(args: Array[String]): Unit = {
    println("Running tests ...")
    runTests()
    println("All test passed")
  }
}
*/

import org.scalatest.{FlatSpec, Matchers}

class NetWorthSpec extends FlatSpec with Matchers {
  "A NetWorth Calculator" should "return 500" in {
    NetWorth.calculate(1000, 500) should be (500)
  }

  "A NetWorth Calculator" should "return 5000" in {
    NetWorth.calculate(10000, 5000) should be (5000)
  }
}