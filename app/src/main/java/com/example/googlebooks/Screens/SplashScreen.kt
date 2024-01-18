package com.example.googlebooks.Screens

import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.googlebooks.Navigation.AllScreens
import com.example.googlebooks.Navigation.Navigations
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    val scale = remember{ androidx.compose.animation.core.Animatable(0f) }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(1f, tween(
            durationMillis = 300,
            delayMillis = 50,
            easing = LinearOutSlowInEasing
        )

        )

        delay(2000L)
//        if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
//        navController.navigate(AllScreens.LoginScreen.name)}
//
//        else
//        { navController.navigate(AllScreens.HomeScreen.name)}

        navController.navigate(AllScreens.LoginScreen.name)

    })
//    Surface(modifier = Modifier.fillMaxSize()) {

Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


    Surface(modifier = Modifier
        .scale(scale.value)
        .size(300.dp),
        shape = CircleShape

        , color= MaterialTheme.colorScheme.surface, border = BorderStroke(1.dp,
            Color.DarkGray), contentColor=MaterialTheme.colorScheme.onSurface
    ) {
        Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "GoogleBooks",  fontSize = 22.sp, fontWeight = FontWeight.ExtraBold,)
            Divider(thickness = 2.dp,modifier= Modifier.size(150.dp,2.dp))
            Text(text = "Read • ImproveYourself")
        }
    }}
//}
    
    
    
}

@Preview
@Composable
fun preSplash(){
    Navigations()
}
