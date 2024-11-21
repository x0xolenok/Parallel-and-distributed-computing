import sbt._

object Dependencies {

  object Version {
    val cats          = "2.12.0"
    val `cats-effect` = "3.5.4"
    val munit         = "1.0.0-M3"
    val spark         = "3.5.3"
    val hadoop        = "3.3.6"
  }

  lazy val cats: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-kernel",
    "org.typelevel" %% "cats-core",
    "org.typelevel" %% "cats-laws"
  ).map(_ % Version.cats)

  lazy val `cats-effect`: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-effect"
  ).map(_ % Version.`cats-effect` withSources() withJavadoc())

  lazy val munit: Seq[ModuleID] = Seq(
    "org.scalameta" %% "munit",
    "org.scalameta" %% "munit-scalacheck"
  ).map(_ % Version.munit % Test withSources() withJavadoc())

  lazy val spark: Seq[ModuleID] = Seq(
    "org.apache.spark" %% "spark-core",
    "org.apache.spark" %% "spark-sql",
    "org.apache.spark" %% "spark-graphx"
  ).map(_ % Version.spark) // % "provided" cross CrossVersion.constant("2.13"))

  lazy  val hadoop: Seq[ModuleID] = Seq(
    "org.apache.hadoop" % "hadoop-client"
  ).map(_ % Version.hadoop)
}