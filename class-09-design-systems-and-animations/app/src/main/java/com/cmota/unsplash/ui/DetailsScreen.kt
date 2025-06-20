package com.cmota.unsplash.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.R
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import com.cmota.unsplash.data.images.UnsplashItem
import com.cmota.unsplash.data.images.Exif
import com.cmota.unsplash.data.images.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import coil3.compose.AsyncImage
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

@Composable
fun DetailsScreen(
  item: UnsplashItem,
  onAction: () -> Unit = {}
) {
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {
    item {
      val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
          .data(item.urls?.regular)
          .build()
      )
      Image(
        painter = painter,
        modifier = Modifier
          .fillMaxWidth()
          .clickable(onClick = onAction),
        contentScale = ContentScale.Crop,
        contentDescription = item.description ?: "Photo"
      )
      // Автор фото
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        AsyncImage(
          model = item.user?.profile_image?.medium,
          contentDescription = item.user?.name ?: "Author",
          modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(20.dp)),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
          text = item.user?.name ?: "-",
          color = Color.White,
          fontWeight = FontWeight.Bold
        )
      }
      Row {
        AddPhotoInfoRow(
          title1 = R.string.info_camera,
          subtitle1 = item.exif?.model ?: "-",
          title2 = R.string.info_aperture,
          subtitle2 = item.exif?.aperture ?: "-",
          textColor = Color.White
        )
      }
      Row {
        AddPhotoInfoRow(
          title1 = R.string.info_focal_length,
          subtitle1 = item.exif?.focal_length ?: "-",
          title2 = R.string.info_shutter_speed,
          subtitle2 = item.exif?.exposure_time ?: "-",
          textColor = Color.White
        )
      }
      Row {
        AddPhotoInfoRow(
          title1 = R.string.info_iso,
          subtitle1 = item.exif?.iso?.toString() ?: "-",
          title2 = R.string.info_dimensions,
          subtitle2 = if (item.width != null && item.height != null) "${item.width} x ${item.height}" else "-",
          textColor = Color.White
        )
      }
      HorizontalDivider(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        thickness = 1.dp,
        color = Color.Gray
      )
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(stringResource(R.string.info_views), color = Color.White)
          Text(item.views?.toString() ?: "-", color = Color.White)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(stringResource(R.string.info_downloads), color = Color.White)
          Text(item.downloads?.toString() ?: "-", color = Color.White)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(stringResource(R.string.info_likes), color = Color.White)
          Text(item.likes?.toString() ?: "-", color = Color.White)
        }
      }
    }
  }
}

@Composable
fun AddPhotoInfoRow(
  @StringRes title1: Int,
  subtitle1: String,
  @StringRes title2: Int,
  subtitle2: String,
  textColor: Color = Color.Unspecified
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Column(
      modifier = Modifier
        .weight(1f)
        .padding(end = 8.dp)
    ) {
      Text(
        text = stringResource(id = title1),
        fontWeight = FontWeight.Bold,
        color = textColor
      )
      Text(text = subtitle1, color = textColor)
    }
    Column(
      modifier = Modifier
        .weight(1f)
        .padding(start = 8.dp)
    ) {
      Text(
        text = stringResource(id = title2),
        fontWeight = FontWeight.Bold,
        color = textColor
      )
      Text(text = subtitle2, color = textColor)
    }
  }
}

@Composable
@Preview
fun PreviewDetailsScreen() {
  DetailsScreen(
    item = UnsplashItem(
      blur_hash = null,
      color = null,
      created_at = null,
      current_user_collections = null,
      description = "La Sagrada Familia",
      height = 4032,
      id = "1",
      liked_by_user = null,
      likes = 1234,
      links = null,
      updated_at = null,
      urls = null,
      user = null,
      width = 3024,
      exif = Exif(
        make = null,
        model = "Canon EOS R",
        name = null,
        exposure_time = "1/200",
        aperture = "f/2.8",
        focal_length = "24mm",
        iso = 100
      ),
      location = Location(
        name = "Barcelona, Spain",
        city = "Barcelona",
        country = "Spain"
      ),
      views = 123456,
      downloads = 7890
    ),
    onAction = {}
  )
}