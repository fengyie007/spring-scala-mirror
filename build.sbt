organization := "org.psnively"

name := "spring-scala"

version := "1.0"

crossScalaVersions := Seq("2.11.6", "2.12.0-M1", "2.10.5")

javacOptions += "-Xlint:-options"

scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:reflectiveCalls") 

val springVersion = "3.2.4.RELEASE"

libraryDependencies ++= Seq(
  "org.springframework"          %  "spring-core"             % springVersion,
  "org.springframework"          %  "spring-beans"            % springVersion,
  "org.springframework"          %  "spring-context"          % springVersion,

  "org.scala-lang"               %  "scala-reflect"           % scalaVersion.value,

  "org.springframework"          %  "spring-aop"              % springVersion          % "optional",
  "org.springframework"          %  "spring-core"             % springVersion          % "optional",
  "org.springframework"          %  "spring-beans"            % springVersion          % "optional",
  "org.springframework"          %  "spring-context"          % springVersion          % "optional",
  "org.springframework"          %  "spring-jdbc"             % springVersion          % "optional",
  "org.springframework"          %  "spring-jms"              % springVersion          % "optional",
  "org.springframework"          %  "spring-web"              % springVersion          % "optional",
  "org.springframework"          %  "spring-test"             % springVersion          % "optional",

  "com.fasterxml.jackson.module" %% "jackson-module-scala"    % "2.4.2"                % "optional",

  "org.apache.geronimo.specs"    %  "geronimo-jms_1.1_spec"   % "1.1"                  % "provided",
  "javax.servlet"                %  "servlet-api"             % "2.5"                  % "provided",
  "javax.inject"                 %  "javax.inject"            % "1"                    % "provided",

  "org.scalatest"                %% "scalatest"               % "2.2.2"                % "test",
  "junit"                        %  "junit"                   % "4.10"                 % "test",
  "org.hsqldb"                   %  "hsqldb-j5"               % "2.2.4"                % "test",
  "log4j"                        %  "log4j"                   % "1.2.16"               % "test",
  "org.springframework"          %  "spring-aspects"          % springVersion          % "test"
)

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    // if scala 2.11+ is used, add dependency on scala-xml module
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.0.3"
      )
    case _ =>
      // or just libraryDependencies.value if you don't depend on scala-swing
      libraryDependencies.value
  }
}
