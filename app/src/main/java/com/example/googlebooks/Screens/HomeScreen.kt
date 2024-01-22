package com.example.googlebooks.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.googlebooks.Navigation.AllScreens
import com.example.googlebooks.R
import com.example.googlebooks.Viewmodel.BookSearchViewmodel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(navController: NavController, viewmodelBook: BookSearchViewmodel){
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