package com.example.googlebooks.Repository

import android.util.Log
import com.example.googlebooks.Model.ZBook
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireRepository @Inject constructor(private val query: Query) {

suspend fun getAllBookFromDatabase(): List<ZBook?> {
    val bookList = query.get().await().documents.map {
        it.toObject(ZBook::class.java)
    }
    Log.d("Reasony", bookList[1]?.publishedDate.toString())
    Log.d("Reasony", bookList[1]?.pageCount.toString())
    Log.d("Reasony", bookList[1]?.rating.toString())
    Log.d("Reasony", bookList[1]?.photoUrl.toString())
    Log.d("Reasony", bookList[1]?.publishedDate.toString())
    return bookList
}

}