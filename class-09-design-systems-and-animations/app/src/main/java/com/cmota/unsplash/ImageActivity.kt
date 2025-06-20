package com.cmota.unsplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cmota.unsplash.ui.theme.UnsplashTheme

class ImageActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val image = intent.getIntExtra("image", R.drawable.ic_android)

    setContent {
      UnsplashTheme {
        ImageScreen(image)
      }
    }
  }
}

@Composable
fun ImageScreen(image: Int) {
  Image(
    painter = painterResource(image),
    contentDescription = stringResource(R.string.description_bcn_sagrada_familia),
    modifier = Modifier.fillMaxSize(),
    contentScale = ContentScale.Crop
  )
}

@Composable
@Preview
fun PreviewImageScreen() {
  ImageScreen(R.drawable.bcn_la_sagrada_familia)
}