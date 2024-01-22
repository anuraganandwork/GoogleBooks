package com.example.googlebooks.Di

import com.example.googlebooks.Constants.constants
import com.example.googlebooks.Model.BookApi
import com.example.googlebooks.Repository.BookRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBookApi(): BookApi
    {
        return  Retrofit.Builder()
            .baseUrl(constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi::class.java)

//         create k under retrofit ka dao hoga
    }

    @Singleton
    @Provides
    fun provideBookRepo(api:BookApi): BookRepo{
        return BookRepo(api)
    }

}