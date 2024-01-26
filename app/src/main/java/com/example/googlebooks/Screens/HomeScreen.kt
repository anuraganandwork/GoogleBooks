package com.example.googlebooks.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(navController: NavController, viewmodelBook: BookSearchViewmodel, Databaseviewmodel:DataViewmodel){
    val username = FirebaseAuth.getInstance().currentUser?.email?.split('@')?.get(0)
    Column {
        Text(text = "This is home screen")
        Icon(painter = painterResource(id = R.drawable.signout), contentDescription ="Sign out",
            modifier = Modifier.clickable {
                FirebaseAuth.getInstance().signOut()
//                    .run {
                navController.navigate(AllScreens.LoginScreen.name)
//                    }
            })
        //viewmodelBook.searchBook("Android")
        Column {


            Text(text = "Welcome, ${username.toString()}")
            Icon(painter = painterResource(id = R.drawable.searchicon),
                contentDescription = "Search",
                modifier = Modifier.clickable {
                    navController.navigate(AllScreens.SearchScreen.name)
                })

            Spacer(modifier = Modifier.padding(20.dp))

//           Text(text = Databaseviewmodel._listOFBook.collectAsState().value.toString())
         val list = Databaseviewmodel._listOFBook.collectAsState().value
           LazyColumn(){
               items(items = list ){
                   if (it != null) {
                       FavBookCard(book = it)
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

fun start(){

}
@Composable
fun  FavBookCard(book: ZBook){
    Surface(shadowElevation = 5.dp, shape = RoundedCornerShape(13.dp)) {
       Column {
//           if (book) {
               AsyncImage(model = ImageRequest.Builder(
                   LocalContext.current)
                   .data(book.photoUrl)
                   .build(), "image of the book",
                   modifier = Modifier
                       .size(140.dp)
                       .padding(10.dp)
               )

               Text(text = book.title.toString(), fontWeight = FontWeight.Bold)
               Spacer(modifier = Modifier.padding(5.dp))
               Text(text = book.authors.toString(), fontSize = 12.sp, fontWeight = FontWeight.Light)

               Log.d("Reason", book.title.toString())
//           }
//           else CircularProgressIndicator()
       }
    }
}