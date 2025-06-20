package com.cmota.unsplash.data.collections

data class UnsplashCollectionItem(
    val cover_photo: CoverPhoto?,
    val description: String?,
    val id: String?,
    val last_collected_at: String?,
    val links: LinksXX?,
    val `private`: Boolean?,
    val published_at: String?,
    val share_key: String?,
    val title: String?,
    val total_photos: Int?,
    val updated_at: String?,
    val user: UserX?
)