package com.example.googlebooks.Navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.googlebooks.Screens.DetailedBookScreen
import com.example.googlebooks.Screens.HomeScreen
import com.example.googlebooks.Screens.LoginScreen
import com.example.googlebooks.Screens.SavedBookDetails
import com.example.googlebooks.Screens.SearchScreen
import com.example.googlebooks.Screens.SignUpScreen
import com.example.googlebooks.Screens.SplashScreen
import com.example.googlebooks.Viewmodel.BookSearchViewmodel
import com.example.googlebooks.Viewmodel.DataViewmodel
import com.example.googlebooks.Viewmodel.LogInViewmodel

@SuppressLint("SuspiciousIndentation")
@Composable
fun Navigations(){

    val navcontroller = rememberNavController()
    val logInViewmodel:LogInViewmodel= viewModel()
    val BookSearchViewmodel:BookSearchViewmodel= hiltViewModel()
    val DataVm:DataViewmodel = hiltViewModel()

    NavHost(navController = navcontroller, startDestination = AllScreens.SplashScreen.name ){
        
        composable(AllScreens.SplashScreen.name){
            SplashScreen(navController = navcontroller)
        }
        composable(AllScreens.HomeScreen.name){
            HomeScreen(navcontroller,BookSearchViewmodel,DataVm)
        }

        composable(AllScreens.LoginScreen.name){
            LoginScreen(navcontroller, logInViewmodel)
        }
        
        composable(AllScreens.SignUpScreen.name){
            SignUpScreen(navController = navcontroller,logInViewmodel)
        }
        
        composable(AllScreens.SearchScreen.name){
            SearchScreen(bookSearchViewmodel = BookSearchViewmodel, navcontroller)
        }

        val detailScreen = AllScreens.DetailedBookScreen.name

        composable("$detailScreen/{BookID}", arguments = listOf(navArgument("BookID"){
            type = NavType.StringType
        })){

          val BookId = it.arguments?.getString("BookID")

            DetailedBookScreen(BookId.toString(), BookSearchViewmodel, navcontroller, DataVm)

        }

        val savedDetailScreen = AllScreens.SavedBookDetails.name
        composable("$savedDetailScreen/{BookID}", arguments = listOf(navArgument("BookID"){
            type = NavType.StringType
        }
        )){
            val BookId = it.arguments?.getString("BookID")
            val firebaseId = it.arguments?.getString("FirebaseID")
           SavedBookDetails(BookID =BookId.orEmpty() , viewmodel =BookSearchViewmodel , navController =navcontroller , Dataviewmodel =DataVm )
           {
               DataVm.getAllBookFromDatabase()
               Log.d("NavigationFunction","Callling")
               navcontroller.navigate(AllScreens.HomeScreen.name)
           }
        }
    }


}