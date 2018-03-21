package lt.markav.inadvisor

import lt.markav.core.PageIds
import org.scalajs.dom
import org.scalajs.dom.document.getElementById
import org.scalajs.dom.Element
import rx._

import scalatags.JsDom.all._
import lt.markav.core.framer.TagsImplicits._
import lt.markav.core.spaider.Path
import lt.markav.core.spaider.SimpleRouter
import lt.markav.inadvisor.widget.DeepWidget
import lt.markav.inadvisor.widget.DeeperWidget
import lt.markav.inadvisor.widget.HomePageWidget

import scalatags.JsDom.all.Frag

object Client extends App {
  implicit val ctx: Ctx.Owner = Ctx.Owner.safe()

  private val mainContainer: Element = getElementById(PageIds.MainContainer)

//  val location = Var(dom.window.location.hash)
//  dom.window.onhashchange = _ => location() = dom.window.location.hash
//  val frag: Frag = Rx { h4(location()) }
//  frag.applyTo(mainContainer)

    new SimpleRouter(mainContainer, List(
      new HomePageWidget("hey"),
      new DeepWidget("one"),
      new DeeperWidget(Path("one") / "two")
    ))

}