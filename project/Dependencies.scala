import sbt._

object Dependencies {
  lazy val flinkScala = "org.apache.flink" %% "flink-scala" % "1.1.0" % "provided"
  lazy val flinkStreaming = "org.apache.flink" %% "flink-streaming-scala" % "1.1-SNAPSHOT" % "provided"
  lazy val flinkClients = "org.apache.flink" %% "flink-clients" % "1.1.0" % "provided"
}
