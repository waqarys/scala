object CompoundInterest extends App {

  def calculate(m: Double, r: Double, t: Int, n: Int = 1): Double = m * ((math.pow(1 + r/n, n * t)) - 1) * (n / r)

  override def main(args: Array[String]): Unit = {
    if(args.length < 3)
      throw new IllegalArgumentException(s"CompoundInterest <principal> <rate> <years>")

    val principalAmount = args(0).toDouble
    val rate = args(1).toDouble
    val numYears = args(2).toInt

    println(f"Your investment of $principalAmount%f will worth ${calculate(principalAmount, rate/100, numYears)}%f in $numYears years at a rate of $rate%f")
  }
}
