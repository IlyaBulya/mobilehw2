package com.cmota.unsplash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cmota.unsplash.ui.AboutScreen
import com.cmota.unsplash.ui.ImagesScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme

enum class TopTab(@StringRes val resId: Int) {
  HOME(R.string.main_tab_images),
  COLLECTIONS(R.string.main_tab_collections)
}

class MainActivity : ComponentActivity() {

  val imagesViewModel: MainViewModel by viewModels()

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    imagesViewModel.fetchPhotos()
    imagesViewModel.fetchCollections()

    setContent {
      UnsplashTheme {

        val images = imagesViewModel.images.observeAsState(emptyList())
        val collections = imagesViewModel.collections.observeAsState(emptyList())

        val error = imagesViewModel.error.observeAsState(null)
        val loading = imagesViewModel.loading.observeAsState(false)

        LaunchedEffect(key1 = error.value) {
          if (error.value != null) {
            Toast.makeText(this@MainActivity, error.value, Toast.LENGTH_SHORT).show()
          }
        }

        val navController = rememberNavController()

        Scaffold(
          containerColor = Color.White,
          modifier = Modifier.fillMaxSize(),
          topBar = {
            CenterAlignedTopAppBar(
              title = {
                Text(stringResource(R.string.app_name))
              }
            )
          },
          floatingActionButton = {
            FloatingActionButton(
              onClick = { Toast.makeText(this@MainActivity, "I ❤️Android", Toast.LENGTH_SHORT).show() }
            ) {
              Icon(
                Icons.Default.Add,
                contentDescription = "Add"
              )
            }
          },
          bottomBar = {
            val actions = listOf(
              BottomNavigationScreen.Home,
              BottomNavigationScreen.About
            )

            val selected = remember { mutableIntStateOf(0) }

            NavigationBar {
              actions.forEachIndexed { index, action ->
                NavigationBarItem(
                  selected = index == selected.intValue,
                  onClick = {
                    selected.intValue = index
                    navController.navigate(action.route)
                  },
                  icon = {
                    Icon(
                      imageVector = action.icon,
                      contentDescription = stringResource(action.resId)
                    )
                  },
                  label = {
                    Text(stringResource(action.resId))
                  }
                )
              }
            }
          }
        ) { innerPadding ->
          Column(
            modifier = Modifier.padding(innerPadding),
          ) {
            NavHost(
              navController = navController,
              startDestination = BottomNavigationScreen.Home.route
            ) {
              composable(BottomNavigationScreen.Home.route) {
                PullToRefreshBox(
                  isRefreshing = loading.value,
                  onRefresh = { imagesViewModel.fetchPhotos() }
                ) {
                  ImagesScreen(
                    images = images.value,
                    collections = collections.value,
                    onOpenDetails = {
                      val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                      intent.putExtra("image", it)
                      startActivity(intent)
                    },
                    onSearchAction = {
                      imagesViewModel.searchPhoto(it)
                    }
                  )
                }
              }

              composable(BottomNavigationScreen.About.route) {
                AboutScreen()
              }
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  UnsplashTheme {
    AboutScreen()
  }
}