package lt.markav.core.facade

import scalajs.js
import org.scalajs.dom.raw._

@js.native
class Material extends js.Object {
  def upgradeElement(element: HTMLElement): Unit = js.native
}

@js.native
@js.annotation.JSGlobalScope
object Material extends js.Object {
  var componentHandler: Material = js.native
}