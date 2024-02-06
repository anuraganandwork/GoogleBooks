package com.example.googlebooks.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Divider
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.googlebooks.Model.ZBook
import com.example.googlebooks.Navigation.AllScreens
import com.example.googlebooks.R
import com.example.googlebooks.Viewmodel.BookSearchViewmodel
import com.example.googlebooks.Viewmodel.DataViewmodel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(navController: NavController, viewmodelBook: BookSearchViewmodel, Databaseviewmodel:DataViewmodel){
    val username = FirebaseAuth.getInstance().currentUser?.email?.split('@')?.get(0)
   val openBottomAppHome  = remember {
       mutableStateOf(true)
   }

    val openSignOutDailog  = remember {
        mutableStateOf(false)
    }
    Scaffold(topBar = {
                      TopAppBar(backgroundColor = Color.White, elevation = 5.dp) {
                          Row(modifier = Modifier
                              .fillMaxWidth()
                              .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {


                              Text(text = "BookSummaryApp")
                              Icon(painter = painterResource(id = R.drawable.signout),
                                  contentDescription = "Sign out",
                                  modifier = Modifier
                                      .size(25.dp)
                                      .clickable {
                                          openSignOutDailog.value = true
                                      })
                          }
                          if (openSignOutDailog.value){
                              AlertDialog(
                                  onDismissRequest = { openSignOutDailog.value = false },
                                  confirmButton = {
                                                  Button(onClick = {
                                                      FirebaseAuth.getInstance().signOut()
                                                      navController.navigate(AllScreens.LoginScreen.name)
                                                  }) {
                                                      Text(text = "Sign out")
                                                  }
                                  },
                                  dismissButton = {
                                      Button(onClick = { openSignOutDailog.value = false  }) {
                                          Text(text = "Go back")
                                      }
                                  },
                                  title = { Text(text = "You are signing out!", fontWeight = FontWeight.Bold)},
                                  text = { Text(text = "Are you sure you ant to sign out?", fontSize = 12.sp, color = Color(
                                      93,
                                      92,
                                      92,
                                      255
                                  )
                                  )}
                                  )
                          }
                      }


                      },
        bottomBar = {
            BottomAppBar(backgroundColor = Color.White, contentColor = Color.Black,
                elevation = 10.dp) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
   Surface(modifier = Modifier
       .padding(start = 20.dp)
       .clickable {
           navController.navigate(AllScreens.HomeScreen.name)
       }
   ) {

      if (openBottomAppHome.value){
          Surface(shape = RoundedCornerShape(15.dp), color = Color(39, 40, 39, 255), contentColor = Color.White) {
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







       if (!openBottomAppHome.value) {
           Icon(
               painterResource(id = R.drawable.home),
               contentDescription = "Home icon",
               modifier = Modifier
                   .size(18.dp)

           )
       }


   }
           // Divider(modifier = Modifier.fillMaxHeight(), thickness = 1.dp)
         Surface(modifier = Modifier
             .padding(end = 20.dp)
             .clickable {
                 openBottomAppHome.value = false
                 navController.navigate(AllScreens.SearchScreen.name)
             }
                 ) {


                    Icon(
                        painterResource(id = R.drawable.searchicon),
                        contentDescription = "Search icon"
                        , modifier = Modifier
                            .size(18.dp)

                    )}
                }
            }
        })
    {


        val list = Databaseviewmodel._listOFBook.collectAsState().value


        Column (modifier = Modifier.padding(it)){
//            Text(text = "This is home screen")
//            Icon(painter = painterResource(id = R.drawable.signout),
//                contentDescription = "Sign out",
//                modifier = Modifier
//                    .size(20.dp)
//                    .clickable {
//
//                    })
            //viewmodelBook.searchBook("Android")
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {


                Text(text = "Welcome, ${username.toString()}", fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top =20.dp))
                Text(text = "Number of books saved : ${list.size}", fontSize = 10.sp
                    , color = Color(
                    135,
                    135,
                    136,
                    255
                )
                )
//                Icon(painter = painterResource(id = R.drawable.searchicon),
//                    contentDescription = "Search",
//                    modifier = Modifier.clickable {
//                    })

                Spacer(modifier = Modifier.padding(20.dp))
       Text(text = "Saved books", fontSize = 20.sp, fontWeight = FontWeight.Medium)
//           Text(text = Databaseviewmodel._listOFBook.collectAsState().value.toString())
                Spacer(modifier = Modifier.padding(15.dp))


                //val list = listOf<String>("ewe","wdwed")
                LazyColumn() {
                    items(items = list) {
                        if (it != null) {
                            FavBookCard(book = it, Modifier.align(Alignment.CenterHorizontally)) {
                                navController.navigate(AllScreens.SavedBookDetails.name + "/${it.id}")
                                Log.d("forId", "${it.id}")
                            }
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                    }

                }


//            if (viewmodelBook._listOfBook.value.data.isNullOrEmpty() == true){
//                CircularProgressIndicator()
//                Log.d("BookApi","ViewIfStat${viewmodelBook._listOfBook.value.data?.get(0)?.volumeInfo?.authors.toString()}")
//               }
//            else {
//                Log.d("BookApi", "from ViewElse${viewmodelBook._listOfBook.value.data?.get(0)?.volumeInfo?.authors.toString()}")
//     val list = viewmodelBook._listOfBook.value.data ?: emptyList()
////               for (i in 0..8)
////                Text(text = viewmodelBook._listOfBook.value.data!![i].volumeInfo.authors.toString())
//       LazyColumn(){
//           items(items = list){
//              Text(text = it.volumeInfo.authors.toString())
//           }
//       }
//            }

            }
        }
    }

}

fun start(){

}
@Composable
fun  FavBookCard(book: ZBook,modifier: Modifier ,call:()->Unit ){
    Surface(shadowElevation = 5.dp, shape = RoundedCornerShape(13.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                call()
            }) {
       Row {
//           if (book) {
               AsyncImage(model = ImageRequest.Builder(
                   LocalContext.current)
                   .data(book.photoUrl)
                   .build(), "image of the book",
                   modifier = Modifier
                       .size(140.dp)
                       .padding(10.dp)
               )
Column (modifier= Modifier.padding(10.dp)){


    Text(text = book.title.toString(), fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.padding(5.dp))
    Text(text = book.authors.toString(), fontSize = 12.sp, fontWeight = FontWeight.Light)
}
               Log.d("Reason", book.title.toString())
//           }
//           else CircularProgressIndicator()
       }
    }
}
//229