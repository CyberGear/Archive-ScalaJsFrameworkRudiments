package lt.markav.core

object ImplicitLogging {

  implicit class Logger[A](a: A) {
    def log(tag: String): A = {
      println(s"$tag: $a")
      a
    }
  }

}
