package com.example.googlebooks.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.googlebooks.Navigation.AllScreens
import com.example.googlebooks.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavController){
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
        Text(text = "Welcome, ${username.toString()}" )
    }


}