package com.example.googlebooks.Model

import com.google.firebase.firestore.Exclude

data class ZBook(
    @Exclude val id: String? = null,

    val title: String? = null,
    val description: String? = null,
    val authors: List<String>? = null,
    val photoUrl: String? = null,
    val pageCount: String? = null,
    val rating: String? = null,
    val publishedDate: String? = null,
    val previewLink: String? = null

    ) {

    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "book_title" to this.title,
            "description" to this.description,
            "authors" to this.authors,
            "photo_Url" to this.photoUrl,
            "page_Count" to this.pageCount,
            "rating" to this.rating,
            "published_Date" to this.publishedDate,
            "preview_Link" to this.previewLink
        )
    }
}
//1138
