package lt.markav.core.util

trait Logging {

  def log(msg: String): Unit = println(msg)

  implicit class ImplLogger[A](a: A) {
    def lg(tag: String): A = {
      println(s"$tag: $a")
      a
    }
  }

}
