import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.example",
  scalaVersion := "2.10.0",
  version      := "0.1.0-SNAPSHOT",
  resolvers += Resolver.mavenLocal,
  libraryDependencies += flinkScala,
  libraryDependencies += flinkStreaming changing(),
  libraryDependencies += flinkClients
)

// Window Word Count example
lazy val windowWordCount = (project in file("WindowWordCount")).
  settings(commonSettings: _*).
  settings(
    mainClass in assembly := Some("flink.samples.WindowWordCount"),
    assemblyJarName in assembly := "WindowWordCount.jar"
  )

// make run command include the provided dependencies
run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run))

// exclude Scala library from assembly
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
