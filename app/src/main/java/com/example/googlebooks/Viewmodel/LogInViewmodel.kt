package com.example.googlebooks.Viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LogInViewmodel : ViewModel() {

   private val auth : FirebaseAuth = Firebase.auth

    private val _Loading = mutableStateOf(true)
    val loading = _Loading

  fun signInWithEmailandPass(email:String, password:String, home:()->Unit){
      viewModelScope.launch {
          try {


      auth.signInWithEmailAndPassword(email,password)
          .addOnCompleteListener {
              if (it.isSuccessful){
//                  go to home
                  Log.d("LoginError","Suucess:;${it.result.toString()}")
                 home()
              }
              else{
                  Log.d("LoginError","${it.result.toString()}")
              }
          }
  }
      catch (e:Exception){
          Log.d("LoginError",e.toString())
      }


      }

  }


    fun LogInWithEmailandPass(email:String, password:String){}
}