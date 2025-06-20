package com.cmota.unsplash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.R

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
  Column(
    modifier = Modifier.fillMaxSize().padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = stringResource(R.string.about_info),
      modifier = modifier,
      textAlign = TextAlign.Center,
      color = Color.Black
    )

    Spacer(modifier = Modifier.height(50.dp))


    Row {
      Image(
        painterResource(R.drawable.ic_android),
        modifier = Modifier
          .size(50.dp),
        contentDescription = "Android Logo"
      )

      Spacer(modifier = Modifier.width(32.dp))

      Image(
        painterResource(R.drawable.ic_kotlin),
        modifier = Modifier
          .size(50.dp),
        contentDescription = "Kotlin Logo"
      )
    }
  }
}
