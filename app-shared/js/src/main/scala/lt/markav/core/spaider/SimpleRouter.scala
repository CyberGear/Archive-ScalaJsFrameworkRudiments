package lt.markav.core.spaider

import org.scalajs.dom.Element
import org.scalajs.dom.window
import rx._

class SimpleRouter(val container: Element, override val widgets: List[Widget]) extends Context {
  private implicit val context: Context = this
  private implicit val ctx: Ctx.Owner = Ctx.Owner.safe()

  val path = Var(Path.current)
  window.onhashchange = _ => path() = Path.current
  val page = Rx { Router.route(path()) }
//  page.now.applyTo(container)


}
