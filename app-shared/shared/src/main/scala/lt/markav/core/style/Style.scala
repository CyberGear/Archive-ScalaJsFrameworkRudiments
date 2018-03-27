package lt.markav.core.style

import sun.font.FontFamily

import scala.language.postfixOps
import scalacss.DevDefaults._
import scalacss.internal.Attr
import scalacss.internal.DslBase.ToStyle
import scalacss.internal.ValueT.TypedAttrBase

object Style extends StyleSheet.Inline {

  import dsl._

  val body = style(
    margin(0 px),
    padding(0 px)
  )

  style("content")(
    maxWidth(1080 px)
  )

  style("card-wide.mdl-card")(
    width(100 %%)
  )

  style("mdl-list__item")(
    position.relative,
    overflow.hidden,

    &.hover(
      backgroundColor(Color("#eee"))
    ),
    &.focus(
      outline.none,
      backgroundColor(Color("#eee"))
    ),
    &.active(
      backgroundColor(Color("#e0e0e0"))
    )
  )

}
