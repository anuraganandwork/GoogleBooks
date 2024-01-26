package com.example.googlebooks.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProduceStateScope
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.googlebooks.Components.DataOrException
import com.example.googlebooks.Data.Item
import com.example.googlebooks.Model.ZBook
import com.example.googlebooks.Viewmodel.BookSearchViewmodel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.collect

@Composable
fun DetailedBookScreen(BookID: String, viewmodel: BookSearchViewmodel){
    //val Book =viewmodel.getParticularBook(BookID)

     viewmodel.getParticularBook(BookID)
     if (viewmodel.Book.value.data?.volumeInfo  == null){
         LinearProgressIndicator()
         Log.d("Detail","${viewmodel.Book.value.data?.volumeInfo?.authors}")
     }
    else {
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
                     .size(76.dp)
                     .clip(CircleShape), contentScale = ContentScale.Crop)

                 Button(onClick = {

                     SaveBookToDatabase(book =
                     ZBook(title = viewmodel.Book.value.data!!.volumeInfo.title,
                          description = viewmodel.Book.value.data!!.volumeInfo.description,
                          authors = viewmodel.Book.value.data!!.volumeInfo.authors,
                         photoUrl = viewmodel.Book.value.data!!.volumeInfo.imageLinks.thumbnail,
                         rating = viewmodel.Book.value.data!!.volumeInfo.maturityRating,
                         publishedDate = viewmodel.Book.value.data!!.volumeInfo.publishedDate,
                         previewLink = viewmodel.Book.value.data!!.volumeInfo.previewLink)
                     )
                 }) {
                     Text(text = "Save this book")
                 }

             Log.d("ImageShow",viewmodel.Book.value.data!!.volumeInfo.imageLinks.thumbnail)
             }
         }

     }
}
 fun SaveBookToDatabase(book: ZBook){
     val db = FirebaseFirestore.getInstance()
     db.collection("SavedBooks").add(book.toMap())
         .addOnCompleteListener {
             documentRef ->
             //db.collection("SavedBooks").document(documentRef.)
             //Toast.makeText(LocalContext.current,"Book Saved!",Toast.LENGTH_SHORT).show()
         Log.d("Saved","inDatabase$documentRef")
         }
 }


//822