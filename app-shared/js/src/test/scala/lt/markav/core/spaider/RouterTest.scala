package lt.markav.core.spaider

import java.util.UUID

import minitest.SimpleTestSuite
import org.scalajs.dom.Element

import scalatags.JsDom
import scalatags.JsDom.all._

object RouterTest extends SimpleTestSuite {

  test("Routes To Home with empty path") {
    val id = rndId

    implicit val widget: Widget = TestWidget(widgets = List(
      TestWidget(id)
    ))

    assertEquals(Router.route(Path()).id, id)
  }

  test("Routes To Home with home path") {
    val path = "home"
    val id = rndId

    implicit val widget: Widget = TestWidget(widgets = List(
      TestWidget(id, path)
    ))

    assertEquals(Router.route(Path(path)).id, id)
  }

  test("Routes To Home with home path") {
    val path = "home"
    val id = rndId

    implicit val widget: Widget = TestWidget(widgets = List(
      TestWidget(id, path)
    ))

    assertEquals(Router.route(Path(path)).id, "fail")
  }

  /** */

  implicit class IdGetter(tag: JsDom.TypedTag[Element]) {
    def id: String = tag.modifiers.head.head.asInstanceOf[StringFrag].v
  }

  def rndId = UUID.randomUUID().toString

}

case class TestWidget(id: String = "",
                      override val path: Path = Path(),
                      override val widgets: List[Widget] = List.empty,
                     ) extends Widget {
  override def contents: Tag = div(id)
}