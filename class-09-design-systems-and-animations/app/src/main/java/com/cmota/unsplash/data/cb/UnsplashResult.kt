package com.cmota.unsplash.data.cb

import com.cmota.unsplash.data.collections.UnsplashCollectionItem
import com.cmota.unsplash.data.images.UnsplashItem

interface UnsplashResult {

  fun onDataFetchedSuccess(images : List<UnsplashItem>)

  fun onDataCollectionFetchedSuccess(collections : List<UnsplashCollectionItem>)

  fun onDataFetchedFailed(message: String)
}