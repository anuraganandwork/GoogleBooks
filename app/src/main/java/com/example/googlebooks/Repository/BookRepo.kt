package com.example.googlebooks.Repository

import android.util.Log
import com.example.googlebooks.Components.DataOrException
import com.example.googlebooks.Data.Item
import com.example.googlebooks.Model.BookApi
import javax.inject.Inject

class BookRepo @Inject constructor(val api:BookApi) {
// suspend fun getBook(bookId: String) : VolumeInfo{
//   return  api.getAllInfo(bookId)
// }

   val dataOrException = DataOrException<List<Item>,Boolean,Exception>()
    val BookdataOrException = DataOrException<Item,Boolean,Exception>()

   suspend fun getBook(bookId: String):DataOrException<List<Item>,Boolean,Exception>{

//      dataOrException.loading = true
       dataOrException.data = api.getAllBooks(bookId).items
// if (dataOrException.data!!.isNotEmpty()){
//     dataOrException.loading= false
// }
     Log.d("BookApi","Repo ${dataOrException.data}")
       return dataOrException
   }


     suspend fun getParticularBooks(Query: String):DataOrException<Item,Boolean,Exception>{

//        BookdataOrException.loading = true
        BookdataOrException.data = api.getInfo(Query)
//        if (BookdataOrException.data.toString().isNotEmpty()){
//            BookdataOrException.loading = false
//        }
       return  BookdataOrException
     }

}