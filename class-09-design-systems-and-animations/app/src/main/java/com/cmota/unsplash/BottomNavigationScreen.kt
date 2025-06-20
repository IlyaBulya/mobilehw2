package com.cmota.unsplash

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreen(
  val route: String,
  @StringRes val resId: Int,
  val icon: ImageVector
) {

  data object Home : BottomNavigationScreen(
    "Home",
    R.string.bottom_tab_home,
    Icons.Outlined.Home
  )

  data object About: BottomNavigationScreen(
    "About",
    R.string.bottom_tab_about,
    Icons.Outlined.Info
  )
}
