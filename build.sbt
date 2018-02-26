import sbt.stringToProcess

lazy val commonSettings = Seq(
  name := "InAdvisor",
  organization := "lt.markav.inadvisor",
  version := "0.1.0",
  scalaVersion := "2.12.3",
  test in assembly := {},
  assemblyJarName in assembly := s"InAdvisor-${version.value}.jar"
)

val ScalatraVersion = "2.6.2"

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    unmanagedSourceDirectories in Compile += baseDirectory.value / "webpage/src",
    unmanagedSourceDirectories in Compile += baseDirectory.value / "backend/src",
    unmanagedResourceDirectories in Compile += baseDirectory.value / "backend/src/main/twirl",
    unmanagedResourceDirectories in Compile += baseDirectory.value / "backend/src/main/webapp",
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
    "copyDevJs" ::
    "backend/jetty:start" ::
    state
}

lazy val copyDevJs = Command.command("copyDevJs") { state =>
  copy(state,
    "backend/target/webapp/js/",
    "webpage/target/scala-2.12/inadvisor-fastopt.js" -> "inadvisor.js",
    "webpage/target/scala-2.12/inadvisor-jsdeps.js" -> "third-party-dependencies.js"
  )
  state
}

def copy(state: State, targetDir: String, direction: (String, String)*): Unit = {
  new File(targetDir).mkdirs()
  direction.map(d => d._1 -> (targetDir + d._2)).foreach(action => {
    s"cp ${action._1} ${action._2}".!(state.log) match {
      case 0 => state.log.info(s"JS file ${action._1} copied to ${action._2}")
      case 1 =>
        state.log.error(s"JS file copy failed: ${action._1} to ${action._2}")
        state.fail
    }
  })
}

clean := {
  val log = streams.value.log
  List("target", "project/target", "backend/target", "webpage/target")
    .foreach(path => s"rm -rf $path".!(log) match {
      case 0 => log.info(s"Deleting $path [OK]")
      case 1 => log.error(s"Deleting $path [FAILED]")
    })
}