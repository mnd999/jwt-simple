// Scala.js
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.1.1")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.18.0")

// Publishing

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.3")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0")

// Resolvers

//addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "0.5.5")

resolvers += Resolver.url("scala-js-snapshots", url("http://repo.scala-js.org/repo/snapshots/"))(
  Resolver.ivyStylePatterns)
