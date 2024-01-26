package com.example.googlebooks.Viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebooks.Model.ZBook
import com.example.googlebooks.Repository.FireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewmodel @Inject constructor(private val repo:FireRepository):ViewModel() {

     private var ListOfBook = MutableStateFlow<List<ZBook?>>(emptyList())
    val _listOFBook = ListOfBook.asStateFlow()

    init {
        getAllBookFromDatabase()
    }
    fun getAllBookFromDatabase(){
        viewModelScope.launch {
           ListOfBook.value = repo.getAllBookFromDatabase()
            Log.d("Reasonn", ListOfBook.value[1]?.title.toString())
        }
    }


}