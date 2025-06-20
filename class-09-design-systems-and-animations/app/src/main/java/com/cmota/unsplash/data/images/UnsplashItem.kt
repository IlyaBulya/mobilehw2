package com.cmota.unsplash.data.images

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashItem(
    val blur_hash: String?,
    val color: String?,
    val created_at: String?,
    val current_user_collections: List<CurrentUserCollection?>?,
    val description: String?,
    val height: Int?,
    val id: String?,
    val liked_by_user: Boolean?,
    val likes: Int?,
    val links: Links?,
    val updated_at: String?,
    val urls: Urls?,
    val user: User?,
    val width: Int?,
    val exif: Exif?,
    val location: Location?,
    val views: Int?,
    val downloads: Int?
) : Parcelable

@Parcelize
data class Exif(
    val make: String?,
    val model: String?,
    val name: String?,
    val exposure_time: String?,
    val aperture: String?,
    val focal_length: String?,
    val iso: Int?
) : Parcelable

@Parcelize
data class Location(
    val name: String?,
    val city: String?,
    val country: String?
) : Parcelable