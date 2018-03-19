package lt.markav.core.spaider

case class Path() {
  def head: Path = new Path
  def tail: Path = new Path
  def notEmpty: Boolean = true
}
