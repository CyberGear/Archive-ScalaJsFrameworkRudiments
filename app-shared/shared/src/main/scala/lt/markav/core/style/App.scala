package lt.markav.core.style

import scalacss.DevDefaults._

object App extends StyleSheet.Inline {
  import dsl._

  val body = style(
    margin(0 px),
    padding(0 px)
  )

}
