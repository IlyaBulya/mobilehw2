package com.cmota.unsplash.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun DetailsScreen(
  @DrawableRes image: Int,
  onAction: (Int) -> Unit = {}
) {
  Column {
    Image(
      painter = painterResource(image),
      modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = {
          onAction(image)
        }),
      contentScale = ContentScale.Crop,
      contentDescription = stringResource(R.string.description_bcn_sagrada_familia)
    )

    Row {
      AddPhotoInfoRow(
        title1 = R.string.info_camera,
        subtitle1 = R.string.info_camera_placeholder,

        title2 = R.string.info_aperture,
        subtitle2 = R.string.info_aperture_placeholder
      )
    }

    Row {
      AddPhotoInfoRow(
        title1 = R.string.info_focal_length,
        subtitle1 = R.string.info_focal_length_placeholder,
        title2 = R.string.info_shutter_speed,
        subtitle2 = R.string.info_shutter_speed_placeholder
      )
    }

    Row {
      AddPhotoInfoRow(
        title1 = R.string.info_iso,
        subtitle1 = R.string.info_iso_placeholder,
        title2 = R.string.info_dimensions,
        subtitle2 = R.string.info_dimensions_placeholder
      )
    }

    HorizontalDivider(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      thickness = 1.dp,
      color = Color.LightGray
    )

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(stringResource(R.string.info_views))

        Text(stringResource(R.string.info_views_placeholder))
      }

      Column(
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(stringResource(R.string.info_downloads))

        Text(stringResource(R.string.info_downloads_placeholder))
      }

      Column(
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(stringResource(R.string.info_likes))

        Text(stringResource(R.string.info_likes_placeholder))
      }
    }
  }
}

@Composable
fun AddPhotoInfoRow(
  @StringRes title1: Int,
  @StringRes subtitle1: Int,

  @StringRes title2: Int,
  @StringRes subtitle2: Int
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
        fontWeight = FontWeight.Bold
      )
      Text(
        text = stringResource(id = subtitle1)
      )
    }

    Column(
      modifier = Modifier
        .weight(1f)
        .padding(start = 8.dp)
    ) {
      Text(
        text = stringResource(id = title2),
        fontWeight = FontWeight.Bold
      )
      Text(
        text = stringResource(id = subtitle2)
      )
    }
  }
}

@Composable
@Preview
fun PreviewDetailsScreen() {
  DetailsScreen(R.drawable.bcn_la_sagrada_familia)
}