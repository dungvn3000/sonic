import sbt._
import Keys._
import sbt.Project._

object SonicBuild extends Build {

  val appOrganization = "com.github"
  val appName = "sonic"
  val appVersion = "0.0.1"
  val appScalaVersion = "2.10.0"

  lazy val sharedSetting = Project.defaultSettings ++ Seq(
    version := appVersion,
    organization := appOrganization,
    scalaVersion := appScalaVersion,
    resolvers ++= Seq(
      "Typesafe Repository" at "http://repo.akka.io/releases/",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      "Expecty Repository" at "https://raw.github.com/pniederw/expecty/master/m2repo/",
      "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
      Resolver.file("Local Repository", file(Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.ivyStylePatterns)
    )
  )

  lazy val sonic = Project(appName, file("."), settings = sharedSetting).aggregate(
    horrorss, sonicParser, sonicWeb
  )

  lazy val sonicParser = Project("sonic_parser", file("sonic_parser"), settings = sharedSetting).settings(
    libraryDependencies ++= coreDependencies ++ parserDependencies ++ testDependencies
  ).dependsOn(horrorss)

  lazy val horrorss = Project("horrorss", file("horrorss"), settings = sharedSetting).settings(
    libraryDependencies ++= coreDependencies ++ parserDependencies ++ testDependencies
  )

  lazy val sonicWeb = play.Project("sonic_web", appVersion, path = file("sonic_web")).dependsOn(sonicParser)

  lazy val coreDependencies = Seq(
    "org.scalaz" %% "scalaz-core" % "7.0.0",
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
    "com.ning" % "async-http-client" % "1.7.16",
    "org.jsoup" % "jsoup" % "1.7.2",
    "org.apache.httpcomponents" % "httpclient" % "4.2.2",
    "org.scalanlp" %% "breeze-process" % "0.3"
  )
}