name := "scala"

version := "0.1"

scalaVersion := "2.13.1"


//custom setting key
lazy val emotion = settingKey[String]("How are you feeling")
emotion := "Fantastic"

val status = settingKey[String]("What tis your current status?")
status := {
  val e = emotion.value
  s"Grateful and $e"
}

//custom task
val randomInt = taskKey[Int]("Give me random number")
randomInt :=  {
  println(emotion.value)
  scala.util.Random.nextInt
}