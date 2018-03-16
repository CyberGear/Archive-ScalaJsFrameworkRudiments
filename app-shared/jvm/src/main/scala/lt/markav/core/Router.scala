package lt.markav.core

import upickle.default

object Router extends autowire.Server[String, default.Reader, default.Writer]{
  def read[Result: default.Reader](p: String): Result = default.read[Result](p)
  def write[Result: default.Writer](r: Result): String = default.write(r)
}
