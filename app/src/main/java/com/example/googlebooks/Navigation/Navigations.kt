package com.example.googlebooks.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googlebooks.Screens.HomeScreen
import com.example.googlebooks.Screens.LoginScreen
import com.example.googlebooks.Screens.SignUpScreen
import com.example.googlebooks.Screens.SplashScreen
import com.example.googlebooks.Viewmodel.LogInViewmodel

@Composable
fun Navigations(){

    val navcontroller = rememberNavController()
    val logInViewmodel:LogInViewmodel= viewModel()

    NavHost(navController = navcontroller, startDestination = AllScreens.SplashScreen.name ){
        
        composable(AllScreens.SplashScreen.name){
            SplashScreen(navController = navcontroller)
        }
        composable(AllScreens.HomeScreen.name){
            HomeScreen()
        }

        composable(AllScreens.LoginScreen.name){
            LoginScreen(navcontroller, logInViewmodel)
        }
        
        composable(AllScreens.SignUpScreen.name){
            SignUpScreen(navController = navcontroller,logInViewmodel)
        }
    }


}