package com.example.googlebooks.Model

import com.google.firebase.firestore.Exclude

data class ZBook(
    val id: String? = null,
    val FirebaseId: String? =null,
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
            "id" to this.id,
            "FirebaseId" to this.FirebaseId,
            "title" to this.title,
            "description" to this.description,
            "authors" to this.authors,
            "photoUrl" to this.photoUrl,
            "pageCount" to this.pageCount,
            "rating" to this.rating,
            "publishedDate" to this.publishedDate,
            "previewLink" to this.previewLink
        )
    }
}
//617

