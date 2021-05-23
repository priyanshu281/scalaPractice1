name := "scalaPractice1"

version := "1.0"

scalaVersion := "2.12.4"

val sparkVersion = "3.1.1"


resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-hive" % sparkVersion,
  "mysql" % "mysql-connector-java" % "5.1.6",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe" % "config" % "1.4.0"
)

/*
sbt 0.13 uses Scala 2.10
sbt 1.x uses Scala 2.12
Also check spark and scala compatibility
check this for sbt compatibilty and crossScala version
https://stackoverflow.com/questions/49000201/whats-the-relationship-of-the-versions-of-scala-when-i-use-sbt-to-build-a-scala
*/
