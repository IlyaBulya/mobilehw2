package com.cmota.unsplash.data.search

import com.cmota.unsplash.data.images.UnsplashItem

data class SearchResult(
  val total: Int? = 0,
  val total_pages: Int? = 0,
  val results: List<UnsplashItem>? = emptyList()
)