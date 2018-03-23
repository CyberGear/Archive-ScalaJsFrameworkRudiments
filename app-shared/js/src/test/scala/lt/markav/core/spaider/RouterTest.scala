package lt.markav.core.spaider

import java.util.UUID

import lt.markav.core.spaider.TestWidget.buildBranch
import minitest.SimpleTestSuite
import org.scalajs.dom.Element

import scalatags.JsDom
import scalatags.JsDom.all._
import scalatags.generic
import scalatags.text.Builder

object RouterTest extends SimpleTestSuite {

  test("Routes to 404 when no widgets added") {
    implicit val widget: Widget = TestWidget()
    warn(widget)
    assertEquals(Router.route(Path()).id, "404")
  }

  test("Routes to home widget with empty path") {
    val id = rndId
    implicit val widget: Widget = TestWidget(widgets = List(TestWidget(id)))
    warn(widget)
    assertEquals(Router.route(Path()).id, id)
  }

  test("Routes to home widget with 'home' path") {
    val id = rndId
    implicit val widget: Widget = TestWidget(widgets = List(TestWidget(id, "home")))
    warn(widget)
    assertEquals(Router.route("home").id, id)
  }

  test("Routes to top widget witch match full path") {
    val id1 = rndId
    val id2 = rndId
    val fullPath = Path("one") / "two" / "three"
    implicit val widget: Widget = TestWidget(widgets = List(
      buildBranch(id1, "one", "two", "three"),
      buildBranch(id2, fullPath)
    ))
    warn(widget)
    assertEquals(Router.route(fullPath).id, id2)
  }

  test("Routes to two level depth") {
    val id = rndId
    val path = Path("one", "two")
    implicit val widget: Widget = TestWidget(widgets = List(
      buildBranch(id, path.parts.map(Path(_)) : _*)
    ))
    warn(widget)
    val value = Router.route(path)
    warn(value.modifiers)
    assertEquals(value.id, id)
  }

  /** */

  implicit class IdGetter(tag: JsDom.TypedTag[Element]) {
    def id: String =  tag.modifiers.head.head.asInstanceOf[StringFrag].v
  }

  private def rndId = UUID.randomUUID().toString.substring(0, 3)

  def warn(str: Any): Unit = {
    def color = (str:String) => Console.BLUE + str + Console.RESET
    println(s"[${color("warn")}] ${color(str.toString)}")
  }

}

case class TestWidget(wid: String = "",
                      override val path: Path = Path(),
                      override val widgets: List[Widget] = List.empty)
  extends Widget {
  override def contents: Tag =
    if (wid.isEmpty) {
      println(s"[info] $path")
      div("", page)
    }
    else {
      println(s"[info] $path($wid)")
      div(wid)
    }

  override def toString: String = s"$path($wid) -> [${widgets.mkString(",")}]"
}

object TestWidget {

  def buildBranch(id: String, paths: Path*): Widget =
    paths.init.foldRight(TestWidget(id, paths.last))(
      (in, out) => TestWidget(path = in, widgets = List(out))
    )

}