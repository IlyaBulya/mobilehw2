package com.cmota.unsplash.api

import com.cmota.unsplash.data.cb.UnsplashResult
import com.cmota.unsplash.data.collections.UnsplashCollectionItem
import com.cmota.unsplash.data.images.UnsplashItem
import com.cmota.unsplash.data.search.SearchResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private val BASE_URL = "https://api.unsplash.com/"

private val retrofit by lazy {
  val interceptor = HttpLoggingInterceptor()
  interceptor.level = HttpLoggingInterceptor.Level.HEADERS

  val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

  val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

  Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create<UnsplashApi>()
}

class UnsplashProvider {

  fun fetchPhotos(cb : UnsplashResult) {
    retrofit.fetchPhotos().enqueue(object : Callback<List<UnsplashItem>> {
      override fun onResponse(
        call: Call<List<UnsplashItem>>,
        response: Response<List<UnsplashItem>>
      ) {
        val output = response.body()
        if (response.isSuccessful && output != null) {
          cb.onDataFetchedSuccess(output)
        } else {
          cb.onDataFetchedFailed("${response.code()}: ${response.errorBody()}")
        }
      }

      override fun onFailure(call: Call<List<UnsplashItem>>, t: Throwable) {
        cb.onDataFetchedFailed("Error: ${t.message}")
      }
    })
  }

  fun searchPhotos(keyword: String, cb: UnsplashResult) {
    retrofit.searchPhotos(keyword).enqueue(object : Callback<SearchResult> {

      override fun onResponse(
        call: Call<SearchResult?>,
        response: Response<SearchResult?>
      ) {
        val output = response.body()
        if (response.isSuccessful && output != null) {
          cb.onDataFetchedSuccess(output.results ?: emptyList())
        } else {
          cb.onDataFetchedFailed("${response.code()}: ${response.errorBody()}")
        }
      }

      override fun onFailure(
        call: Call<SearchResult?>,
        t: Throwable
      ) {
        cb.onDataFetchedFailed("Error: ${t.message}")
      }
    })
  }

  fun fetchCollections(cb: UnsplashResult) {
    retrofit.fetchCollections().enqueue(object : Callback<List<UnsplashCollectionItem>> {
      override fun onResponse(
        call: Call<List<UnsplashCollectionItem>>,
        response: Response<List<UnsplashCollectionItem>>
      ) {
        val output = response.body()
        if (response.isSuccessful && output != null) {
          cb.onDataCollectionFetchedSuccess(output)
        } else {
          cb.onDataFetchedFailed("${response.code()}: ${response.errorBody()}")
        }
      }

      override fun onFailure(call: Call<List<UnsplashCollectionItem>>, t: Throwable) {
        cb.onDataFetchedFailed("Error: ${t.message}")
      }
    })
  }
}