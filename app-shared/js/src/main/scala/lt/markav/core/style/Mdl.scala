package lt.markav.core.style

import lt.markav.core.facade.Material
import org.scalajs.dom.html.Element

import scala.language.implicitConversions
import scalatags.JsDom

object Mdl {
  val layout: TagClass = "mdl-layout"
  val jsLayout: TagClass = "mdl-js-layout"
  val layoutFixedHeader: TagClass = "layout--fixed-header"

  val layoutHeader: TagClass = "mdl-layout__header"
  val layoutHeaderRow: TagClass = "mdl-layout__header-row"
  val layoutTitle: TagClass = "mdl-layout-title"
  val layoutSpacer: TagClass = "mdl-layout-spacer"
  val button: TagClass = "mdl-button"
  val jsButton : TagClass = "mdl-js-button"
  val buttonFab : TagClass = "mdl-button--fab"
  val jsRippleEffect : TagClass = "mdl-js-ripple-effect"
  val buttonColored : TagClass = "mdl-button--colored"
  val shadow4dp: TagClass = "mdl-shadow--4dp"

  val layoutContent: TagClass = "mdl-layout__content"
  val colorGrey100: TagClass = "mdl-color--grey-100"
  val grid: TagClass = "mdl-grid"

  val mdlCard: TagClass = "mdl-card"
  val mdlShadow2dp: TagClass = "mdl-shadow--2dp"
  val mdlCell: TagClass = "mdl-cell"

  val title: TagClass = "mdl-title"
  val cardTitle: TagClass = "mdl-card__title"

  val list: TagClass = "mdl-list"

  val listItem: TagClass = "mdl-list__item"
  val listItemThreeLine: TagClass = "mdl-list__item--three-line"
  val listItemPrimaryContent: TagClass = "mdl-list__item-primary-content"

  val listItemAvatar: TagClass = "mdl-list__item-avatar"
  val colorBlue800: TagClass = "mdl-color--blue-800"
  val colorTextBlue800: TagClass = "mdl-color-text--blue-800"
  val layoutLargeScreenOnly: TagClass = "mdl-layout--large-screen-only"

  val listItemTextBody: TagClass = "mdl-list__item-text-body"
  val colorTextRed400: TagClass = "mdl-color-text--red-400"
  val colorTextGreen500: TagClass = "mdl-color-text--green-500"

  val listItemSecondaryContent: TagClass = "mdl-list__item-secondary-content"
  val ripple: TagClass = "mdl-ripple"

  implicit class ImplicitMaterialUpgrade(tag: JsDom.TypedTag[Element]) {
    def material: Element = {
      val element = tag.render
      Material.componentHandler.upgradeElement(element)
      element
    }
  }

}

