package com.example.googlebooks.Screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.googlebooks.Model.ZBook
import com.example.googlebooks.Navigation.AllScreens
import com.example.googlebooks.R
import com.example.googlebooks.Viewmodel.BookSearchViewmodel
import com.example.googlebooks.Viewmodel.DataViewmodel
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedBookDetails(BookID: String,viewmodel: BookSearchViewmodel, navController: NavController, Dataviewmodel: DataViewmodel, call:()-> Unit){
    //val Book =viewmodel.getParticularBook(BookID)
    val context = LocalContext.current

    val _savedBook = remember { mutableStateOf<ZBook?>(null) }

    val db = FirebaseFirestore.getInstance()
    val bookDocumentRef = db.collection("SavedBooks").document(BookID)
        .get().addOnSuccessListener {
                val savedBook = it.toObject(ZBook::class.java)
                 _savedBook.value = savedBook

        }

    //scaffold

    Scaffold(topBar = {
        TopAppBar( elevation = 5.dp, backgroundColor = Color.White, contentColor = Color.Black) {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {

                Icon(painter = painterResource(id = R.drawable.arrow), contentDescription = "Go back",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { navController.popBackStack() }, tint = Color.Black)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = _savedBook.value?.title.toString(), fontSize = 18.sp)

            }
        }
    }){
Column(modifier = Modifier
    .padding(it)
    .verticalScroll(rememberScrollState())) {


    if (_savedBook != null) {

        showSavedBook(book = _savedBook.value){
            Dataviewmodel.getAllBookFromDatabase()
            navController.navigate(AllScreens.HomeScreen.name)
        }
    }
    Log.d("Deleting","$BookID is the book id")
   // viewmodel.getParticularBook(BookID)
//    if (viewmodel.Book.value.data?.volumeInfo  == null){
//        LinearProgressIndicator()
//        Log.d("Detail","${viewmodel.Book.value.data?.volumeInfo?.authors}")
//    }

   /* else {
        Log.d("Detail","Else${viewmodel.Book.value.data?.volumeInfo?.authors}")

        Surface {
            Column(modifier = Modifier
                .verticalScroll(
                    rememberScrollState()

                )
                .fillMaxSize()) {
                viewmodel.Book.value.data?.volumeInfo?.let { Text(text = it.title) }
                //viewmodel.Book.value.data?.volumeInfo?.let { Text(text = it.description) }
                AsyncImage(model
                // = "http://books.google.com/books/content?id=GpSAtAEACAAJ&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE72Eb6FArqqt9bSkbr5oGCReisiST8SBXs-ncrzsBotnoO-KveDKy_nV5MMcJFLOGQ6uNhIexQsk-kBdbVzP4FZPLfq4zuvDTqzXtN-VqQkf9yLHdOf-RZoFZboS-hhy1XT1WsQR&source=gbs_api"
                // = viewmodel.Book.value.data!!.volumeInfo.imageLinks.thumbnail
                = ImageRequest.Builder(LocalContext.current)
                    //.data("http://books.google.com/books/content?id=GpSAtAEACAAJ&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE72Eb6FArqqt9bSkbr5oGCReisiST8SBXs-ncrzsBotnoO-KveDKy_nV5MMcJFLOGQ6uNhIexQsk-kBdbVzP4FZPLfq4zuvDTqzXtN-VqQkf9yLHdOf-RZoFZboS-hhy1XT1WsQR&source=gbs_api")
                    .data(viewmodel.Book.value.data!!.volumeInfo.imageLinks.thumbnail)
                    .size(Size.ORIGINAL) // Set the target size to load the image at.
                    .build()
                    ,"", modifier = Modifier
                    , contentScale = ContentScale.Crop)

              Button(onClick = { FirebaseFirestore.getInstance()
                  .collection("SavedBooks").document(firebaseId).delete()
                  .addOnCompleteListener {
                      if (it.isSuccessful) {
                          Toast.makeText(context, "Book delete", Toast.LENGTH_SHORT).show()
                        //  Log.d("Deleting", it.result.toString())
                         call()
                      }
                      else{
                         // Log.d("Deleting",it.exception.toString())
                      }

                  }}) {
                  Text(text = "Delete")
              }

                Log.d("ImageShow",viewmodel.Book.value.data!!.volumeInfo.imageLinks.thumbnail)
            }
        }
*/
    }}}
   @OptIn(ExperimentalMaterial3Api::class)
   @SuppressLint("UnrememberedMutableState")
   @Composable
fun showSavedBook(book: ZBook?, call: () -> Unit){
       val openDailog = remember {
           mutableStateOf(false)
       }
       //Scaffold






       if (book != null) {
           Column {







               AsyncImage(
                   model = ImageRequest.Builder(LocalContext.current)
                       .data(book.photoUrl).build(), contentDescription = " Image of the book"
               , modifier = Modifier
                       .size(225.dp)
                       .padding(15.dp)
                       .align(Alignment.CenterHorizontally))


               Button(onClick = {
                   openDailog.value = true
               },  modifier = Modifier.align(Alignment.CenterHorizontally)) {
                   Text(text = "Delete")
               }


               Log.d("AlertBoxOutside",openDailog.value.toString())
               if (openDailog.value == true){
                   Log.d("AlertBox",openDailog.value.toString())
                 AlertDialog(onDismissRequest = { openDailog.value = false},
                       confirmButton = {
                           Button(onClick = {  
                               
                           
                           book.id?.let {
                               FirebaseFirestore.getInstance()
                                   .collection("SavedBooks").document(it).delete()
                                   .addOnCompleteListener {
                                       if (it.isSuccessful) {
                                           // Toast.makeText(context, "Book delete", Toast.LENGTH_SHORT).show()
                                           //  Log.d("Deleting", it.result.toString())
                                          openDailog.value = false
                                           call()
                                       } else {
                                           // Log.d("Deleting",it.exception.toString())
                                       }
                                   }
                           }}){ Text(text = "Yes")}
                       },
                     dismissButton = {
                                     Button(onClick = {
                                         openDailog.value = false
                                     }) {
                                         Text(text = "Go back")
                                     }
                     },
                      title = { Text(text = "Delete ${book.title}", fontWeight = FontWeight.Bold)},
                      text = { Text(text = "Are you share you want to delete this book?", fontSize = 12.sp, color = Color(93,
                          92,
                          92,
                          255))}
                     // icon = { painterResource(R.drawable.danger)}

                 )
               }

               Column(modifier = Modifier.padding(10.dp)) {


                   Text(text = "Auther", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                   Text(text = book.authors.toString(), color = Color(
                       69,
                       68,
                       68,
                       255
                   )
                   )
                   Spacer(modifier = Modifier.padding(12.dp))
                   Text(
                       text = "Summary of this book",
                       fontSize = 20.sp,
                       fontWeight = FontWeight.Bold
                   )
                   Spacer(modifier = Modifier.padding(5.dp))
                   Text(text = book.description.toString(), color = Color(69,
                       68,
                       68,
                       255))
               }



           }




       }}