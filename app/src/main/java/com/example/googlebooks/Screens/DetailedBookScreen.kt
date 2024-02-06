package com.example.googlebooks.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.example.googlebooks.R

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProduceStateScope
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.googlebooks.Components.DataOrException
import com.example.googlebooks.Data.Item
import com.example.googlebooks.Model.ZBook
import com.example.googlebooks.Navigation.AllScreens
import com.example.googlebooks.Viewmodel.BookSearchViewmodel
import com.example.googlebooks.Viewmodel.DataViewmodel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun DetailedBookScreen(BookID: String, viewmodel: BookSearchViewmodel, navController: NavController, Dataviewmodel: DataViewmodel){
    //val Book =viewmodel.getParticularBook(BookID)
  val context = LocalContext.current



     viewmodel.getParticularBook(BookID)
     if (viewmodel.Book.value.data?.volumeInfo  == null){
         LinearProgressIndicator()
         Log.d("Detail","${viewmodel.Book.value.data?.volumeInfo?.authors}")
     }
    else {
         Log.d("Detail","Else${viewmodel.Book.value.data?.volumeInfo?.authors}")

//Scaffold

         Scaffold(topBar = {
             TopAppBar( elevation = 5.dp, backgroundColor = Color.White, contentColor = Color.Black) {
                 Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {

                     Icon(painter = painterResource(id = R.drawable.arrow), contentDescription = "Go back",
                         modifier = Modifier
                             .size(20.dp)
                             .clickable { navController.popBackStack() }, tint = Color.Black)
                     Spacer(modifier = Modifier.padding(10.dp))
                     Text(text = viewmodel.Book.value.data!!.volumeInfo.title, fontSize = 18.sp)

                 }
             }
         })
         {

         Surface(modifier = Modifier.padding(it)) {
             Column(modifier = Modifier
                 .verticalScroll(
                     rememberScrollState()

                 )
                 .fillMaxSize()) {
                //viewmodel.Book.value.data?.volumeInfo?.let { Text(text = it.description) }
             AsyncImage(model
             // = "http://books.google.com/books/content?id=GpSAtAEACAAJ&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE72Eb6FArqqt9bSkbr5oGCReisiST8SBXs-ncrzsBotnoO-KveDKy_nV5MMcJFLOGQ6uNhIexQsk-kBdbVzP4FZPLfq4zuvDTqzXtN-VqQkf9yLHdOf-RZoFZboS-hhy1XT1WsQR&source=gbs_api"
             // = viewmodel.Book.value.data!!.volumeInfo.imageLinks.thumbnail
             = ImageRequest.Builder(LocalContext.current)
                 //.data("http://books.google.com/books/content?id=GpSAtAEACAAJ&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE72Eb6FArqqt9bSkbr5oGCReisiST8SBXs-ncrzsBotnoO-KveDKy_nV5MMcJFLOGQ6uNhIexQsk-kBdbVzP4FZPLfq4zuvDTqzXtN-VqQkf9yLHdOf-RZoFZboS-hhy1XT1WsQR&source=gbs_api")
                 .data(viewmodel.Book.value.data!!.volumeInfo.imageLinks.thumbnail)
                 //.size(Size.ORIGINAL) // Set the target size to load the image at.
                 .build()
                 ,"", modifier = Modifier
                     .size(225.dp)
                     .padding(15.dp)
                     .align(Alignment.CenterHorizontally)
                     )


                 Button(onClick = {

                     SaveBookToDatabase(book =
                     ZBook(id= viewmodel.Book.value.data!!.id,
                         FirebaseId = "",
                         title = viewmodel.Book.value.data!!.volumeInfo.title,
                          description = viewmodel.Book.value.data!!.volumeInfo.description,
                          authors = viewmodel.Book.value.data!!.volumeInfo.authors,
                         photoUrl = viewmodel.Book.value.data!!.volumeInfo.imageLinks.thumbnail,
                         rating = viewmodel.Book.value.data!!.volumeInfo.maturityRating,
                         publishedDate = viewmodel.Book.value.data!!.volumeInfo.publishedDate,
                         previewLink = viewmodel.Book.value.data!!.volumeInfo.previewLink),
                        context){
                         Dataviewmodel.getAllBookFromDatabase()
                         //navController.navigate(AllScreens.HomeScreen.name)
                     }


                     Log.d("Reason","goingdata"+viewmodel.Book.value.data!!.volumeInfo.title)

                 }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                     Text(text = "Save this book")
                 }

             Log.d("ImageShow",viewmodel.Book.value.data!!.volumeInfo.imageLinks.thumbnail)

               Column(modifier = Modifier.padding(10.dp)) {


                   Text(text = "Auther", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                   Text(text = viewmodel.Book.value.data!!.volumeInfo.authors.toString(), color = Color(
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
                   Text(text = viewmodel.Book.value.data!!.volumeInfo.description, color = Color(69,
                       68,
                       68,
                       255))
               }





             }
         }}

     }
}

 fun SaveBookToDatabase(book: ZBook, context: Context, call:()->Unit ){
     val db = FirebaseFirestore.getInstance()

     db.collection("SavedBooks").add(book.toMap())
         .addOnCompleteListener {
             //db.collection("SavedBooks").document(documentRef.)
         val docId = it.result.id
   db.collection("SavedBooks").document(docId)
       .update(hashMapOf("id" to docId) as Map<String, Any>)
             Toast.makeText(context,"Book Saved!",Toast.LENGTH_SHORT).show()
         Log.d("Saved","inDatabase$it")
             if (it.isSuccessful){
                 call()
             }
             //Toast.makeText(LocalContext.current,"Book Saved!",Toast.LENGTH_SHORT).show()
         Log.d("Saved","inDatabase$it")
         }
 }


//1103