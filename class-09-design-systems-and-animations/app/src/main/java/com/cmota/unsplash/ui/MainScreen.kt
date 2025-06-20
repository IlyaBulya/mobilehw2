package com.cmota.unsplash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.cmota.unsplash.R
import com.cmota.unsplash.data.images.UnsplashItem

@Composable
fun MainScreen(
  images: List<UnsplashItem>,
  onAction: (UnsplashItem) -> Unit,
  onSearchAction: (String) -> Unit
) {
  LazyColumn(
    contentPadding = PaddingValues(16.dp)
  ) {
    item {
      val search = rememberSaveable { mutableStateOf("") }

      OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = search.value,
        onValueChange = {
          search.value = it
        },
        leadingIcon = {
          Icon(
            Icons.Default.Search,
            contentDescription = stringResource(R.string.description_search)
          )
        },
        label = {
          Text(stringResource(R.string.main_search_hint))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
          imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions {
          onSearchAction(search.value)
        }
      )
    }

    item {
      Spacer(modifier = Modifier.height(16.dp))
    }

    items(images) { image ->
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Blue.copy(alpha = 0.25f))
            .clickable(onClick = { onAction(image) })
        ) {
          Surface {
            val painter = rememberAsyncImagePainter(
              model = ImageRequest.Builder(LocalContext.current)
                .data(image.urls?.regular)
                .build()
            )

            Image(
              painter = painter,
              contentDescription = stringResource(R.string.description_image),
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )

            Column(
              modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
              verticalArrangement = Arrangement.Bottom
            ) {
              Text(
                text = image.user?.username ?: "-",
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .background(Color.LightGray)
                  .padding(start = 8.dp, end = 8.dp)
              )

              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = image.description ?: "-",
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .background(Color.LightGray)
                  .padding(start = 8.dp, end = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
              )
            }
          }
        }

      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}