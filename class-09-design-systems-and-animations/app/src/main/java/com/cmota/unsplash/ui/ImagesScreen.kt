package com.cmota.unsplash.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.cmota.unsplash.TopTab
import com.cmota.unsplash.data.collections.UnsplashCollectionItem
import com.cmota.unsplash.data.images.UnsplashItem

@Composable
fun ImagesScreen(
  images: List<UnsplashItem>,
  collections: List<UnsplashCollectionItem>,
  onOpenDetails: (UnsplashItem) -> Unit,
  onSearchAction: (String) -> Unit
) {
  val actions = listOf(TopTab.HOME, TopTab.COLLECTIONS)

  val selected = rememberSaveable { mutableIntStateOf(TopTab.HOME.ordinal) }

  Column {
    TabRow(
      selectedTabIndex = selected.intValue
    ) {
      actions.forEachIndexed { index, action ->
        Tab(
          selected = index == selected.intValue,
          onClick = { selected.intValue = index },
          text = {
            Text(text = stringResource(action.resId))
          }
        )
      }
    }

    when (selected.intValue) {
      TopTab.HOME.ordinal -> {
        MainScreen(
          images = images,
          onAction = { image -> onOpenDetails(image) },
          onSearchAction = onSearchAction
        )
      }

      TopTab.COLLECTIONS.ordinal -> {
        CollectionsScreen(
          images = collections
        )
      }
    }
  }
}