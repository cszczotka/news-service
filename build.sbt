name := """news-service"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin)
scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies ++= Seq(
  evolutions,
  jdbc,
  "com.h2database" % "h2" % "1.4.199",
  "org.playframework.anorm" %% "anorm" % "2.6.10"
)
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
swaggerDomainNameSpaces := Seq("models")

libraryDependencies += "org.webjars" % "swagger-ui" % "3.43.0"
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
