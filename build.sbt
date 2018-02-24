val ScalatraVersion = "2.6.2"
lazy val backend = (project in file("backend"))
  .enablePlugins(SbtTwirl)
  .enablePlugins(ScalatraPlugin)
  .settings(
    organization := "com.example",
    name := "My Scalatra Web App",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.12.4",
    resolvers += Classpaths.typesafeReleases,
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra" % ScalatraVersion,
      "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
      "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
      "org.eclipse.jetty" % "jetty-webapp" % "9.4.8.v20171121" % "container",
      "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
    )
  )

lazy val webpage = (project in file("webpage"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "InAdvisor",
    version := "0.1",
    scalaVersion := "2.12.4",
    scalaJSUseMainModuleInitializer := true,
    skip in packageJSDependencies := false,
    libraryDependencies ++= Seq(
      "be.doeraene" %%% "scalajs-jquery" % "0.9.1"
    ),
    jsDependencies ++= Seq(
      "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"
    )
  )

lazy val launch = taskKey[Unit]("Launch solution")
launch := {
//  fastOptJS in webpage
//  JettyPlugin.jettyRunner in ContainerPlugin.start in backend
}