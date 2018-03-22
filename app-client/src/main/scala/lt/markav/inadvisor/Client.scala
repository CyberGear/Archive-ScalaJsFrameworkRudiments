package lt.markav.inadvisor

import lt.markav.core.PageIds
import lt.markav.core.spaider.Path
import lt.markav.core.spaider.RootWidget
import lt.markav.core.spaider.SimpleRouter
import lt.markav.inadvisor.widget.DeepWidget
import lt.markav.inadvisor.widget.DeeperWidget
import lt.markav.inadvisor.widget.HomePageWidget
import lt.markav.inadvisor.widget.MainFrameWidget
import org.scalajs.dom.Element
import org.scalajs.dom.document.getElementById
import rx._

object Client extends App {
  implicit val ctx: Ctx.Owner = Ctx.Owner.safe()

  private val mainContainer: Element = getElementById(PageIds.MainContainer)

//  new SimpleRouter(mainContainer, List(

//  ))

  new RootWidget(mainContainer, List(
    new MainFrameWidget(List(
      new HomePageWidget("hey"),
      new DeeperWidget(Path("one") / "two"),
      new DeepWidget("one")
    ))
  ))

}