package lt.markav.inadvisor.template

import lt.markav.core.style.MaterialIcons
import lt.markav.core.style.Mdl
import lt.markav.core.style.TagClass
import org.scalajs.dom.html.Element

import scalatags.JsDom
import scalatags.JsDom.all._

sealed class Advice(val label: String, val color: TagClass)

object Advice {
  case object Buy extends Advice("Buy", Mdl.colorTextGreen500)
  case object Sell extends Advice("Sell", Mdl.colorTextRed400)
  case object Wait extends Advice("Wait", Mdl.colorTextBlue800)
}

case class WatchListItem(label: String,
                         isTrending: Boolean,
                         advice: Advice,
                         advisor: String,
                         currentPosition: String,
                         diff: String,
                         diffInPercent: String) extends TagsGroup {

  private val icon = if (isTrending) MaterialIcons.TrendingUp else MaterialIcons.TrendingDown
  private val diffColor = if (diff.startsWith("-")) Mdl.colorTextRed400 else Mdl.colorTextGreen500

  override def generate: JsDom.TypedTag[Element] = li(Mdl.listItem, Mdl.listItemThreeLine, Mdl.jsRippleEffect,
    span(Mdl.listItemPrimaryContent,
      icon(Mdl.listItemAvatar, Mdl.colorBlue800, Mdl.layoutLargeScreenOnly),
      span(label),
      span(Mdl.listItemTextBody,
        div(advice.color, advice.label),
        advisor)),
    span(Mdl.listItemSecondaryContent,
      currentPosition,
      span(diffColor, diff),
      span(diffColor, diffInPercent)),
    span(Mdl.ripple))

}
