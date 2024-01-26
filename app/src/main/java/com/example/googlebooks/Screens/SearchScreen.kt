package com.example.googlebooks.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.googlebooks.Data.Item
import com.example.googlebooks.Navigation.AllScreens
import com.example.googlebooks.Viewmodel.BookSearchViewmodel

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(bookSearchViewmodel: BookSearchViewmodel, navController: NavController){
    val searchedBook = remember{
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var listofBook = bookSearchViewmodel._listOfBook.value.data ?: emptyList()
    val showProgressBar = mutableStateOf(false)
    Column(modifier = Modifier.padding(10.dp)) {
       TextField(value = searchedBook.value,
                 onValueChange = {Usertyped -> searchedBook.value = Usertyped},
                 modifier = Modifier.fillMaxWidth(),
           label = { Text(text = "Enter topic/name")},
           keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
           keyboardActions = KeyboardActions(onSearch = {
               showProgressBar.value = true
               bookSearchViewmodel.searchBook(searchedBook.value)
               focusManager.clearFocus()
               keyboardController?.hide()
               searchedBook.value = ""

           })
       )
      Spacer(modifier = Modifier.padding(25.dp))

       // if (listofBook.isEmpty()) CircularProgressIndicator()
        LaunchedEffect(key1 = showProgressBar.value){
            if (showProgressBar.value && listofBook.isEmpty()){
                showProgressBar.value = false
                }
        }


 if (showProgressBar.value ) CircularProgressIndicator()
    else   LazyColumn(){
            items(items = listofBook){
                BookCardForSearched(Book = it,navController){
                  listofBook = emptyList()
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }

            }
        }



    }

@Composable
fun BookCardForSearched(Book: Item, navController: NavController, call:()->Unit){

    val imageOfbook = if ( Book.volumeInfo.imageLinks.smallThumbnail.isEmpty()) {
        "https://books.google.com/books/content?id=qM7UswEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        }
        else{
            Book.volumeInfo?.imageLinks?.thumbnail
        }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate(AllScreens.DetailedBookScreen.name + "/${Book.id}")
            call()
        }, shape = RoundedCornerShape(10.dp),
            elevation = 5.dp) {



        if (Book.volumeInfo.toString().isEmpty()) CircularProgressIndicator()
        else {
           // Row {
                Row(modifier = Modifier.padding(10.dp)) {
//           if (Book.volumeInfo.imageLinks.smallThumbnail.isNotEmpty()){
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(
//                            imageOfbook
//                           // "${Book.volumeInfo.imageLinks.smallThumbnail}"
//                           //"https://books.google.com/books/content?id=qM7UswEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
//                        )
//                        .crossfade(true)
//                        .build(),
//                    contentDescription = "Image of the book is loading",
//                    onLoading = {Log.d("BookApi","Loading image")},
//                    onSuccess = {Log.d("BookApi","Success image")},
//                    onError = {Log.d("BookApi","Error in loading image " +
//                            "${Book.volumeInfo.imageLinks.thumbnail}")},
//                    modifier = Modifier.clip(CircleShape)
//                )
//
                Image(painter = rememberAsyncImagePainter(
                    model =ImageRequest.Builder(LocalContext.current)
                    .data(Book.volumeInfo.imageLinks.thumbnail)
                    .size(Size.ORIGINAL) // Set the target size to load the image at.
                    .build() ),
                    "IMage")



          // }



                   Column {
                       Text(text = Book.volumeInfo.title)
                       Text(text = "Author : ${Book.volumeInfo.authors}", fontSize = 10.sp)
                   }


               Log.d("BookApi","${Book.volumeInfo.imageLinks.smallThumbnail}")
                }
           // }
        }
    }
    
}
//904