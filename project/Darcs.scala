import java.io.File
import xml._
import sbt.Process
import sbtrelease.{ Vcs, VcsCompanion }

class Darcs(val baseDir: File) extends Vcs {
  private lazy val exec = executableName(commandName)
  val commandName: String = "darcs"
  def cmd(args: Any*): sbt.ProcessBuilder = Process(exec +: args.map(_.toString), baseDir)
  def status: sbt.ProcessBuilder = cmd("whatsnew") #| List("fgrep", "-v", "No changes!") #|| "cat"
  def add(files: String*): sbt.ProcessBuilder = cmd(("add" +: files): _*)
  def commit(message: String): sbt.ProcessBuilder = cmd("record", "-m", message)
  def currentHash: String = {
    val last = XML.loadString(cmd("log", "--last=1", "--xml")!!)
    (last \ "patch").head.attribute("hash").get.head.text
  }
  def pushChanges: sbt.ProcessBuilder = cmd("push")
  def checkRemote(remote: String): sbt.ProcessBuilder = cmd("pull", remote)
  def tag(name: String,comment: String,force: Boolean): sbt.ProcessBuilder = cmd("tag", "-m", comment, name)
  def existsTag(name: String): Boolean = {
    val tags = (cmd("show", "tags")!!).split('\n')
    tags.contains(name)
  }
  def currentBranch: String = ""
  def trackingRemote: String = {
    val pat = "^Default Remote\\: (.+)$".r
    val repo = (cmd("show", "repo")!!)
    repo match {
      case pat(remote) => remote
      case _ => ""
    }
  }
  def isBehindRemote: Boolean = !trackingRemote.isEmpty
  def hasUpstream: Boolean = isBehindRemote
}

object Darcs extends VcsCompanion {
  protected val markerDirectory = "_darcs"

  def mkVcs(baseDir: File) = new Darcs(baseDir)

  def apply(dir: File): Option[Vcs] = isRepository(dir).map(mkVcs(_))
}
