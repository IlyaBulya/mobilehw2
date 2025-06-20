package com.cmota.unsplash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cmota.unsplash.ui.DetailsScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme

class DetailsActivity : ComponentActivity() {

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val image = intent.getIntExtra("image", R.drawable.ic_kotlin)

    setContent {
      UnsplashTheme {
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          topBar = {
            TopAppBar(
              navigationIcon = {
                IconButton(onClick = { finish() }) {
                  Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back"
                  )
                }
              },
              title = {
                Text(stringResource(R.string.app_name))
              }
            )
          },
          content = { innerPadding ->
            Column(
              modifier = Modifier.padding(innerPadding),
            ) {
              DetailsScreen(
                image = image,
                onAction = { resId ->
                  val intent = Intent(this@DetailsActivity, ImageActivity::class.java)
                  intent.putExtra("image", resId)
                  startActivity(intent)
                }
              )
            }
          }
        )
      }
    }
  }
}