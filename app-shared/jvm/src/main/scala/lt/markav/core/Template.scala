package lt.markav.core

import scalacss.DevDefaults._

object Template extends StyleSheet.Inline {
  import dsl._

  val body = style(
    margin(0 px),
    padding(0 px)
  )

}
