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

//  test("Routes to home widget with empty path") {
//    val id = rndId
//    implicit val widget: Widget = TestWidget(widgets = List(TestWidget(id)))
//    assertEquals(Router.route(Path()).id, id)
//  }

//  test("Routes to home widget with 'home' path") {
//    val path = "home"
//    val id = rndId
//    implicit val widget: Widget = TestWidget(widgets = List(TestWidget(id, path)))
//    assertEquals(Router.route(path).id, id)
//  }

//  test("Routes to top widget witch match full path") {
//    val id1 = rndId
//    val id2 = rndId
//    val fullPath = Path("one") / "two" / "three"
//    implicit val widget: Widget = TestWidget(widgets = List(
//      buildBranch(id1, "one", "two", "three"),
//      buildBranch(id2, fullPath)
//    ))
//    assertEquals(Router.route(fullPath).id, id2)
//  }

  test("Routes to deep node of the branch") {
    val id = rndId
    implicit val widget: Widget = TestWidget(widgets = List(
      buildBranch(id, "one", "two", "three")
    ))
    println(widget)
    assertEquals(Router.route(Path("one") / "two" / "three").id, id)
  }

  /** */

  implicit class IdGetter(tag: JsDom.TypedTag[Element]) {
    def id: String = {
      println(tag.modifiers)
      type Attr = generic.AttrPair[Builder, String]
      val attr = tag.modifiers.head.find(_.isInstanceOf[AttrPair]).map(_.asInstanceOf[Attr])
      attr match {
        case Some(a) => a.v
        case None => "undefined"
      }
    }
  }

  private def rndId = UUID.randomUUID().toString

}

case class TestWidget(wid: String = "",
                      override val path: Path = Path(),
                      override val widgets: List[Widget] = List.empty)
  extends Widget {
  override def contents: Tag =
    if (wid.isEmpty) div("")
    else div(wid, page)
}

object TestWidget {

  def buildBranch(id: String, paths: Path*): Widget =
    paths.init.foldRight(TestWidget(id, paths.last))(
      (in, out) => TestWidget(path = in, widgets = List(out))
    )

}