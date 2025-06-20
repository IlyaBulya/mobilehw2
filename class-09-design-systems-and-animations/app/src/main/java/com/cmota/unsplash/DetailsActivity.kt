package com.cmota.unsplash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cmota.unsplash.api.UnsplashProvider
import com.cmota.unsplash.data.cb.UnsplashResult
import com.cmota.unsplash.data.images.UnsplashItem
import com.cmota.unsplash.data.collections.UnsplashCollectionItem
import com.cmota.unsplash.ui.DetailsScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import android.os.Build
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

class DetailsActivity : ComponentActivity() {

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    window.navigationBarColor = android.graphics.Color.BLACK
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = false
    val photoId = intent.getStringExtra("photo_id")

    setContent {
      UnsplashTheme {
        var details by remember { mutableStateOf<UnsplashItem?>(null) }
        var error by remember { mutableStateOf<String?>(null) }
        var loading by remember { mutableStateOf(true) }

        LaunchedEffect(photoId) {
          if (photoId != null) {
            UnsplashProvider().fetchPhotoDetails(photoId, object : UnsplashResult {
              override fun onDataFetchedSuccess(images: List<UnsplashItem>) {
                details = images.firstOrNull()
                loading = false
              }
              override fun onDataFetchedFailed(message: String) {
                error = message
                loading = false
              }
              override fun onDataCollectionFetchedSuccess(collections: List<UnsplashCollectionItem>) {
                // ничего не делаем
              }
            })
          } else {
            error = "No photo id"
            loading = false
          }
        }

        Scaffold(
          modifier = Modifier.fillMaxSize(),
          topBar = {
            TopAppBar(
              navigationIcon = {
                IconButton(onClick = { finish() }) {
                  Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                  )
                }
              },
              title = {
                Text(
                  stringResource(R.string.app_name),
                  color = Color.White
                )
              },
              colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
              )
            )
          },
          content = { innerPadding ->
            Column(
              modifier = Modifier.padding(innerPadding),
            ) {
              when {
                loading -> Text("Загрузка...")
                error != null -> Text("Ошибка: $error")
                details != null -> DetailsScreen(
                  item = details!!,
                  onAction = {}
                )
              }
            }
          }
        )
      }
    }
  }
}