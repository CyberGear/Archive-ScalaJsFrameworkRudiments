package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Path
import lt.markav.core.spaider.Widget
import lt.markav.inadvisor.template.Advice
import lt.markav.inadvisor.template.WatchList
import lt.markav.inadvisor.template.WatchListItem
import lt.markav.inadvisor.template.WideCard

case class AlarmsWidget(override val path: Path = "alarms") extends Widget {
  override def contents: Tag = WideCard(
    WatchList(
      WatchListItem("S&P 500 (^GSPC)", true, Advice.Buy, "First MK Advisor", "2,640.87", "+35.87", "(+1.38%)"),
      WatchListItem("Bitcoin USD (BTC-USD)", false, Advice.Wait, "Dangis Advisor", "7,166.73", "-80.25", "(+1.29%)"),
      WatchListItem("NASDAQ Composite (^IXIC)", false, Advice.Sell, "First MK Advisor", "7,063.44", "+114.22", "(+1.64%)")
    ).generate,
    "Watch List").generate
}
