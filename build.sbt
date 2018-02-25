import sbt.stringToProcess

lazy val commonSettings = Seq(
  name := "InAdvisor",
  organization := "lt.markav.inadvisor",
  version := "0.1.0",
  scalaVersion := "2.12.3"
)

val ScalatraVersion = "2.6.2"

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    unmanagedSourceDirectories in Compile += baseDirectory.value / "webpage/src",
    unmanagedSourceDirectories in Compile += baseDirectory.value / "webpage/web",
    unmanagedSourceDirectories in Compile += baseDirectory.value / "backend/src",
    commands ++= Seq(launch, copyDevJs)
  )

lazy val backend = (project in file("backend"))
  .enablePlugins(SbtTwirl)
  .enablePlugins(ScalatraPlugin)
  .settings(
    commonSettings,
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
    commonSettings,
    scalaJSUseMainModuleInitializer := true,
    skip in packageJSDependencies := false,
    libraryDependencies ++= Seq(
      "be.doeraene" %%% "scalajs-jquery" % "0.9.1"
    ),
    jsDependencies ++= Seq(
      "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"
    )
  )

lazy val launch = Command.command("launch") { state =>
  "webpage/fastOptJS" ::
    "backend/jetty:start" ::
    state
}

lazy val copyDevJs = Command.command("copyDevJs") { state =>
  List(
    List("webpage/target/scala-2.12/inadvisor-fastopt.js", "backend/src/main/resources/inadvisor.js"),
    List("webpage/target/scala-2.12/inadvisor-jsdeps.js", "backend/src/main/resources/libs.js")
  ).foreach(action => {
    s"cp ${action.head} ${action.last}".!(state.log) match {
      case 0 => state.log.info(s"JS file ${action.head} copied to ${action.last}")
      case 1 => state.log.error(s"JS file copy failed: ${action.head} to ${action.last}")
    }
  })
  state
}


