package com.cmota.unsplash.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.cmota.unsplash.R
import com.cmota.unsplash.data.collections.UnsplashCollectionItem

@Composable
fun CollectionsScreen(images: List<UnsplashCollectionItem>) {
  Log.d("CollectionsScreen", "images: ${images.size}")
  LazyVerticalGrid(
    columns = GridCells.Fixed(3),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(8.dp)
  ) {

    items(images) { image ->
      val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
          .data(image.cover_photo?.urls?.regular)
          .build()
      )

      Image(
        painter = painter,
        contentDescription = stringResource(R.string.description_image),
        modifier = Modifier
          .fillMaxSize()
          .aspectRatio(1.0f),
        contentScale = ContentScale.Crop
      )
    }
  }
}