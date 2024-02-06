package com.example.googlebooks.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomAppBar
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.googlebooks.R
import com.example.googlebooks.Viewmodel.BookSearchViewmodel
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(bookSearchViewmodel: BookSearchViewmodel, navController: NavController){
    val searchedBook = remember{
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current



    val listofBook =
       mutableStateOf(bookSearchViewmodel._listOfBook.value.data ?: emptyList())


//    fun refreshList() {
//        listofBook.value = bookSearchViewmodel._listOfBook.value.data ?: emptyList()
//    }


val listOgBook = bookSearchViewmodel._listOfBook
Log.d("CheckRecomp",listOgBook.toString())

    Log.d("Checkit", listofBook.toString())
    val showProgressBar = remember {

    mutableStateOf(false)}

    //scaffold starts
  val openSearchbottomBar = remember {
      mutableStateOf(true)
  }
    Scaffold(

        bottomBar = {
            BottomAppBar(backgroundColor = Color.White, contentColor = Color.Black,
                elevation = 10.dp) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {


                    androidx.compose.material3.Surface(modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable {
                            navController.navigate(AllScreens.HomeScreen.name)
                        }
                    ) {

                        if (!openSearchbottomBar.value){
                            androidx.compose.material3.Surface(shape = RoundedCornerShape(15.dp), color = Color(39, 40, 39, 255), contentColor = Color.White) {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                                    Icon(painterResource(id = R.drawable.home),
                                        contentDescription = "Home icon",
                                        modifier = Modifier
                                            .size(18.dp)

                                    )
                                    Spacer(modifier = Modifier.padding(4.dp))
                                    Text(text = "Home", fontSize = 10.sp)
                                }
                            }
                        }







                        if (openSearchbottomBar.value) {
                            Icon(
                                painterResource(id = R.drawable.home),
                                contentDescription = "Home icon",
                                modifier = Modifier
                                    .size(18.dp)
                                    .clickable {
                                        openSearchbottomBar.value = false
                                        navController.navigate(AllScreens.HomeScreen.name)
                                    }

                            )
                        }


                    }
                    // Divider(modifier = Modifier.fillMaxHeight(), thickness = 1.dp)

                    if (!openSearchbottomBar.value)
                    androidx.compose.material3.Surface(modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            //  openBottomAppHome.value = false
                            //navController.navigate(AllScreens.SearchScreen.name)
                        }
                    ) {


                        Icon(
                            painterResource(id = R.drawable.searchicon),
                            contentDescription = "Search icon"
                            , modifier = Modifier
                                .size(18.dp)

                        )
                    }

                    if (openSearchbottomBar.value){
                        androidx.compose.material3.Surface(shape = RoundedCornerShape(15.dp), color = Color(39, 40, 39, 255), contentColor = Color.White) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                                Icon(painterResource(id = R.drawable.searchicon),
                                    contentDescription = "Search icon",
                                    modifier = Modifier
                                        .size(18.dp)

                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = "Search", fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
        }) {

Column(modifier = Modifier.padding(it)) {


        Column(modifier = Modifier.padding(10.dp)) {
            TextField(
                value = searchedBook.value,
                onValueChange = { Usertyped -> searchedBook.value = Usertyped },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Enter topic/name") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    showProgressBar.value = true
                    bookSearchViewmodel.searchBook(searchedBook.value)
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    searchedBook.value = ""
                    //refreshList()

                })
            )

            // if (listofBook.isEmpty()) CircularProgressIndicator()
            //LaunchedEffect(key1 = showProgressBar.value) {
//                if (showProgressBar.value && listofBook.isEmpty()) {
//                    showProgressBar.value = false
//                }
//                if (listofBook.isNullOrEmpty()
//                ){
//                    showProgressBar.value = true
//                }

               // bookSearchViewmodel.searchBook(searchedBook.value)

           if (listofBook.value.isNotEmpty()){showProgressBar.value = false}


            if (showProgressBar.value) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

            else LazyColumn(modifier = Modifier.padding(top = 25.dp)) {
                items(items = listofBook.value) {
                    BookCardForSearched(Book = it, navController) {
                        listofBook.value = emptyList()
                    }

                    Spacer(modifier = Modifier.padding(10.dp))
                }

            }
        }}
    }


    }

@Composable
fun BookCardForSearched(Book: Item, navController: NavController, call:()->Unit){



    Surface(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate(AllScreens.DetailedBookScreen.name + "/${Book.id}")
        }, shape = RoundedCornerShape(10.dp),

        elevation = 5.dp) {




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



                   Column(modifier = Modifier.padding(10.dp)) {
                       Text(text = Book.volumeInfo.title)
                       Text(text = "Author : ${Book.volumeInfo.authors}", fontSize = 10.sp)
                   }


               Log.d("BookApi","${Book.volumeInfo.imageLinks.smallThumbnail}")
                }
           // }
        }
    }
    

//226