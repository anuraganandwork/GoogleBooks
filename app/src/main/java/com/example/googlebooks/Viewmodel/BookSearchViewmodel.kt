package com.example.googlebooks.Viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebooks.Components.DataOrException
import com.example.googlebooks.Data.Item
import com.example.googlebooks.Repository.BookRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BookSearchViewmodel @Inject  constructor(val repo: BookRepo)
    : ViewModel(){

       private val listOfBook:MutableState< DataOrException<List<Item>, Boolean ,Exception>> =
           mutableStateOf(DataOrException(null, true, Exception("")))

    private val _Book:MutableState< DataOrException<Item, Boolean ,Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))


       val _listOfBook = listOfBook
    val Book = _Book

    @SuppressLint("SuspiciousIndentation")
    fun searchBook(nameOfBook : String){
        viewModelScope.launch {
            listOfBook.value.loading= true
            listOfBook.value=  repo.getBook(nameOfBook)

            if (listOfBook.value.data?.isNotEmpty() == true){
                listOfBook.value.loading=false
            }
        }
        Log.d("Book","viewmodel${listOfBook.value.data?.get(0)?.volumeInfo?.authors}")

    }

     fun getParticularBook(BookID : String) {
         viewModelScope.launch {
      _Book.value=  repo.getParticularBooks(BookID)
    }

     }

}


//1246