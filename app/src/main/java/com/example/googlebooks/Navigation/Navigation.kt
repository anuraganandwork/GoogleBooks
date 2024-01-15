package com.example.googlebooks.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googlebooks.Screens.HomeScreen
import com.example.googlebooks.Screens.SplashScreen

@Composable
fun Navigations(){

    val navcontroller = rememberNavController()

    NavHost(navController = navcontroller, startDestination = AllScreens.SplashScreen.name ){
        
        composable(AllScreens.SplashScreen.name){
            SplashScreen(navController = navcontroller)
        }
        composable(AllScreens.HomeScreen.name){
            HomeScreen()
        }
    }


}