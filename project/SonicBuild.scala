import sbt._
import Keys._
import Project._

object SonicBuild extends Build {

  lazy val sharedSetting = defaultSettings ++ Seq(
    version := "0.0.1",
    organization := "com.github.sonic",
    scalaVersion := "2.10.0",
    scalacOptions += "-Yresolve-term-conflict:package",
    resolvers ++= Seq(
      "Typesafe Repository" at "http://repo.akka.io/releases/",
      "twitter4j" at "http://twitter4j.org/maven2",
      "clojars.org" at "http://clojars.org/repo",
      "thischwa-repro" at "http://maven-repo.thischwa.de/",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      "Expecty Repository" at "https://raw.github.com/pniederw/expecty/master/m2repo/",
      "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
      Resolver.file("Local Repository", file(Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.ivyStylePatterns)
    )
  )

  lazy val sonic = Project("sonic", file("."), settings = sharedSetting).aggregate(
    sonicCore, sonicModel, sonicParser, sonicExtractor, linkerzParser
  )

  lazy val sonicCore = Project("sonic_core", file("sonic_core"), settings = sharedSetting).settings(
    libraryDependencies ++= coreDependencies ++ testDependencies
  )

  lazy val sonicModel = Project("sonic_model", file("sonic_model"), settings = sharedSetting).settings(
  ).dependsOn(sonicCore)

  lazy val sonicParser = Project("sonic_parser", file("sonic_parser"), settings = sharedSetting).settings(
    libraryDependencies ++= parserDependencies ++ testDependencies
  ).dependsOn(sonicExtractor)

  lazy val linkerzParser = Project("linkerz_parser", file("linkerz_parser"), settings = sharedSetting).settings(
    libraryDependencies ++= parserDependencies ++ testDependencies
  ).dependsOn(sonicExtractor)

  lazy val sonicExtractor = Project("sonic_extractor", file("sonic_extractor"), settings = sharedSetting).settings(
    libraryDependencies ++= testDependencies
  ).dependsOn(sonicModel)

  lazy val coreDependencies = Seq(
    "org.slf4j" % "slf4j-simple" % "1.6.6",
    "org.slf4j" % "slf4j-api" % "1.6.6",
    "commons-collections" % "commons-collections" % "3.2.1",
    "commons-digester" % "commons-digester" % "2.1" exclude("commons-beanutils", "commons-beanutils"),
    "commons-lang" % "commons-lang" % "2.6",
    "commons-validator" % "commons-validator" % "1.4.0" exclude("commons-beanutils", "commons-beanutils")
  )

  lazy val testDependencies = Seq(
    "junit" % "junit" % "4.10" % "test",
    "org.expecty" % "expecty" % "0.9" % "test",
    "org.scalatest" %% "scalatest" % "1.9.1" % "test"
  )

  lazy val parserDependencies = Seq(
    "org.jsoup" % "jsoup" % "1.7.2",
    "org.apache.httpcomponents" % "httpclient" % "4.2.2",
    "org.scalanlp" %% "breeze-process" % "0.2-SNAPSHOT"
  )
}