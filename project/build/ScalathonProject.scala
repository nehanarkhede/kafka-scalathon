import sbt._

class ScalathonProject(info: ProjectInfo) extends DefaultProject(info) {
  override def repositories = Set(ScalaToolsSnapshots, "JBoss Maven 2 Repository" at "http://repository.jboss.com/maven2",
    "linkedin repo" at "http://esv4-cm01.corp.linkedin.com:8081/artifactory/ext-libraries",
    "Oracle Maven 2 Repository" at "http://download.oracle.com/maven", "maven.org" at "http://repo2.maven.org/maven2/")

  override def artifactID = "scalathon-kafka"
  override def filterScalaJars = false
  override def ivyXML =
    <dependencies>
        <exclude module="javax"/>
        <exclude module="jmxri"/>
        <exclude module="jmxtools"/>
        <exclude module="mail"/>
        <exclude module="jms"/>
    </dependencies>

  val log4j = "log4j" % "log4j" % "1.2.15"
  val jopt = "net.sf.jopt-simple" % "jopt-simple" % "3.2"
  val kafka = "kafka" % "kafka" % "0.6.0"

  override def javaCompileOptions = super.javaCompileOptions ++
    List(JavaCompileOption("-source"), JavaCompileOption("1.5"))
}
