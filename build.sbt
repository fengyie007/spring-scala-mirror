import java.io.File

lazy val scalaMinorVersion = settingKey[String]("The minor version of Scala used to build the project.")
lazy val springVersion = settingKey[String]("The version of Spring used to build the project.")

lazy val root = (project in file(".")).
  settings(
    organization := "org.psnively",
    name := "spring-scala",
    version := "1.0.0",
    crossScalaVersions := Seq("2.11.7", "2.12.0-M1", "2.10.5"),
    scalaMinorVersion := scalaVersion.value.split('.').take(2).mkString("."),
    unmanagedSourceDirectories in Compile += (sourceDirectory in Compile).value / scalaMinorVersion.value,
    javacOptions ++= Seq("-source", "1.7", "-target", "1.7", "-Xlint:-options"),
    scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:reflectiveCalls"),
    springVersion := "3.2.4.RELEASE",
    libraryDependencies ++= Seq(
      "org.springframework"          %  "spring-core"             % springVersion.value,
      "org.springframework"          %  "spring-beans"            % springVersion.value,
      "org.springframework"          %  "spring-context"          % springVersion.value,

      "org.scala-lang"               %  "scala-reflect"           % scalaVersion.value,

      "org.springframework"          %  "spring-aop"              % springVersion.value          % "optional",
      "org.springframework"          %  "spring-core"             % springVersion.value          % "optional",
      "org.springframework"          %  "spring-beans"            % springVersion.value          % "optional",
      "org.springframework"          %  "spring-context"          % springVersion.value          % "optional",
      "org.springframework"          %  "spring-jdbc"             % springVersion.value          % "optional",
      "org.springframework"          %  "spring-jms"              % springVersion.value          % "optional",
      "org.springframework"          %  "spring-web"              % springVersion.value          % "optional",
      "org.springframework"          %  "spring-test"             % springVersion.value          % "optional",

      "com.fasterxml.jackson.module" %% "jackson-module-scala"    % "2.4.2"                      % "optional",

      "org.apache.geronimo.specs"    %  "geronimo-jms_1.1_spec"   % "1.1"                        % "provided",
      "javax.servlet"                %  "servlet-api"             % "2.5"                        % "provided",
      "javax.inject"                 %  "javax.inject"            % "1"                          % "provided",

      "org.scalatest"                %% "scalatest"               % "2.2.4"                      % "test",
      "junit"                        %  "junit"                   % "4.12"                       % "test",
      "org.hsqldb"                   %  "hsqldb"                  % "2.3.2"                      % "test",
      "log4j"                        %  "log4j"                   % "1.2.16"                     % "test",
      "org.springframework"          %  "spring-aspects"          % springVersion.value          % "test"
    ),
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
    },
    parallelExecution in Test := false,
    externalDependencyClasspath in Test += new File(System.getProperty("java.home"), "lib" + File.separator + "resources.jar")
  )
