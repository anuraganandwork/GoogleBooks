package com.example.googlebooks.Model

import com.example.googlebooks.Data.BApi
import com.example.googlebooks.Data.Item
import com.example.googlebooks.Data.VolumeInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BookApi {

//    just like Dao


    @GET("volumes")
   suspend fun getAllBooks(@Query("q") query : String):BApi

   @GET("volumes/{bookId}")
   suspend fun getInfo(@Path("bookId") bookId : String): Item
}