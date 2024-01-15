package com.example.googlebooks.Screens

import android.window.SplashScreen
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.googlebooks.Navigation.AllScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    val scale = remember{ androidx.compose.animation.core.Animatable(0f) }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(.9f,animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        ))


        delay(3000)
        navController.navigate(AllScreens.HomeScreen.name)
    })
    Surface(shape = CircleShape) {
        Column {
            Text(text = "GoogleBooks", fontSize = 22.sp)
            Text(text = "Read • ChangeYourself")
        }
    }
    
    
    
}