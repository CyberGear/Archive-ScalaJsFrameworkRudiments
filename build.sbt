import scala.sys.process.stringToProcess

lazy val gitCommitCount = "git rev-list HEAD --count".!!.replace("\n", "")
lazy val gitCommitHash = "git log --pretty=format:'%h' -n 1".!!.replace("'", "").replace("\n", "")

lazy val commonSettings = Seq[Def.Setting[_]](
  scalaVersion := "2.12.4",
  organization := "lt.markav",
  name := "InAdvisor",
  version := s"0.1.$gitCommitCount",
  assemblyJarName in assembly := s"${name.value}-${version.value}.jar",
  assemblyOutputPath in assembly := baseDirectory.value / ".." / (assemblyJarName in assembly value),
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:implicitConversions",
    "-language:postfixOps"
  )
)

lazy val appShared = (crossProject in file("app-shared"))
  .settings(commonSettings: _*)
  .settings(
    name := "InAdvisorShared",
    unmanagedSourceDirectories in Compile += baseDirectory.value / "shared" / "main" / "scala",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "scalatags" % "0.6.2",
      "com.lihaoyi" %%% "upickle" % "0.4.4",
      "com.lihaoyi" %%% "autowire" % "0.2.6",
      "com.lihaoyi" %%% "scalarx" % "0.3.2",
      "com.github.japgolly.scalacss" %%% "core" % "0.5.5",
      "com.github.japgolly.scalacss" %%% "ext-scalatags" % "0.5.5"
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "be.doeraene" %%% "scalajs-jquery" % "0.9.2",
      "io.monix" %%% "minitest" % "2.1.1" % "test"
    ),
    jsDependencies ++= Seq(
      "org.webjars" % "jquery" % "2.1.3" / "2.1.3/jquery.js"
    ),
    testFrameworks += new TestFramework("minitest.runner.Framework")
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.1.0",
      "com.typesafe.akka" %% "akka-actor" % "2.5.11",
      "com.typesafe.akka" %% "akka-stream" % "2.5.11",
      "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.2.1",
      "org.webjars" % "bootstrap" % "3.2.0"
    )
  )
lazy val appSharedJS = appShared.js
lazy val appSharedJVM = appShared.jvm

lazy val appServer = (project in file("app-server"))
  .settings(commonSettings: _*)
  .settings(
    (resources in Compile) += (fastOptJS in(appClient, Compile)).value.data,
    (resources in Compile) += (packageJSDependencies in(appClient, Compile)).value
  ).dependsOn(appSharedJVM)

lazy val appClient = (project in file("app-client"))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    skip in packageJSDependencies := false,
    libraryDependencies += "io.monix" %%% "minitest" % "2.1.1" % "test",
    testFrameworks += new TestFramework("minitest.runner.Framework")
  ).dependsOn(appSharedJS)

lazy val root = (project in file("."))
  .aggregate(appSharedJS, appSharedJVM, appClient, appServer)
  .settings(commands += launch)

lazy val launch = Command.command("launch") { state => "appServer/reStart" :: state }

aggregate in assembly := false

assembly in root := (assembly in appServer).value

lazy val runJar = TaskKey[Unit]("runJar")
runJar := {
  val path = (assembly in appServer).value
  s"java -jar $path".!
}
