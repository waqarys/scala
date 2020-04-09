import com.typesafe.sbt.packager.docker.ExecCmd

name := "scala"

scalaVersion := "2.13.1"

ThisBuild / version := "1.0"
ThisBuild / licenses ++= Seq(("MIT", url("http://opensource.org/licenses/MIT")))

publish/skip := true
bintrayVcsUrl := Some("git@github.com:waqarys/scala.git")

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


lazy val root = project.in(file("."))
  .aggregate(calculators)

lazy val calculators = project
    .dependsOn(api)
    .enablePlugins(JavaAppPackaging)
    .enablePlugins(DockerPlugin)
    .settings(
      libraryDependencies ++= Dependencies.calculatorDependencies,
      dockerCommands := dockerCommands.value.filterNot {
        case ExecCmd("ENTRYPOINT", _) => true
        case _ => false
      },
      dockerCommands ++= Seq(ExecCmd("ENTRYPOINT", "/opt/docker/bin/net-worth"))
    )

lazy val api = project
    .enablePlugins(JavaAppPackaging)
    .settings(
      libraryDependencies ++= Dependencies.apiDependencies
    )

resolvers += Resolver.JCenterRepository
/*
lazy val test = project.settings(
  libraryDependencies += ("calculators" % "calculators_2.13.1" % "1.0")
)
*/
