import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys.{libraryDependencies, _}
import sbt._

import scala.language.postfixOps

val scalaJsIOVersion = "0.5.0"
val apiVersion = scalaJsIOVersion
val scalaJsVersion = "2.13.3"

homepage := Some(url("https://github.com/scalajs-io/jwt-simple"))

lazy val root = (project in file(".")).
  enablePlugins(ScalaJSPlugin).
  enablePlugins(ScalaJSBundlerPlugin).
  settings(
    name := "jwt-simple",
    organization := "io.scalajs.npm",
    description := "Jwt-simple API bindings for Scala.js",
    version := apiVersion,
    scalaVersion := scalaJsVersion,
    scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions", "-Xlint"),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalacOptions in(Compile, doc) ++= Seq("-no-link-warnings"),
    autoCompilerPlugins := true,
    libraryDependencies ++= Seq(
	    "org.scala-lang" % "scala-reflect" % scalaJsVersion,
	    "org.scalatest" %%% "scalatest" % "3.2.2" % "test"
    ),
    npmDependencies in Test ++= Seq(
	    "jwt-simple" -> "0.5.6"
    )
)

/////////////////////////////////////////////////////////////////////////////////
//      Publishing
/////////////////////////////////////////////////////////////////////////////////

lazy val publishingSettings = Seq(
  sonatypeProfileName := "org.xerial",
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  pomExtra :=
    <url>https://github.com/scalajs-io/jwt-simple</url>
      <licenses>
        <license>
          <name>MIT License</name>
          <url>http://www.opensource.org/licenses/mit-license.php</url>
        </license>
      </licenses>
      <scm>
        <connection>scm:git:github.com/scalajs-io/jwt-simple.git</connection>
        <developerConnection>scm:git:git@github.com:scalajs-io/jwt-simple.git</developerConnection>
        <url>github.com/scalajs-io/jwt-simple.git</url>
      </scm>
      <developers>
        <developer>
          <id>ldaniels528</id>
          <name>Lawrence Daniels</name>
          <email>lawrence.daniels@gmail.com</email>
          <organization>io.scalajs</organization>
          <organizationUrl>https://github.com/scalajs-io</organizationUrl>
          <roles>
            <role>Project-Administrator</role>
            <role>Developer</role>
          </roles>
          <timezone>+7</timezone>
        </developer>
      </developers>
)

// loads the Scalajs-io root project at sbt startup
onLoad in Global := (Command.process("project root", _: State)) compose (onLoad in Global).value
