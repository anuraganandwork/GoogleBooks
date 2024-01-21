package com.example.googlebooks.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.googlebooks.Data.Item
import com.example.googlebooks.Viewmodel.BookSearchViewmodel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(bookSearchViewmodel: BookSearchViewmodel){
    val searchedBook = remember{
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val listofBook = bookSearchViewmodel._listOfBook.value.data ?: emptyList()

    Column(modifier = Modifier.padding(10.dp)) {
       TextField(value = searchedBook.value,
                 onValueChange = {Usertyped -> searchedBook.value = Usertyped},
                 modifier = Modifier.fillMaxWidth(),
           label = { Text(text = "Enter topic/name")},
           keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
           keyboardActions = KeyboardActions(onSearch = {
               bookSearchViewmodel.searchBook(searchedBook.value)
               keyboardController?.hide()
               searchedBook.value = ""

           })
       )

        LazyColumn(){
            items(items = listofBook){
                BookCardForSearched(Book = it)
            }
        }



    }
}
@Composable
fun BookCardForSearched(Book: Item){
    Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp),
            elevation = 5.dp) {


    Column {
        if (Book.volumeInfo?.toString().isNullOrEmpty()) CircularProgressIndicator()
        else {
            Text(text = Book.volumeInfo.title)
            Text(text = "Author : ${Book.volumeInfo.authors}", fontSize = 10.sp)
        }
    }
    
}}
