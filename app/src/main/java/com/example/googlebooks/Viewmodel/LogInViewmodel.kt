package com.example.googlebooks.Viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebooks.Model.ZUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
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


    fun createUserWithEmailandPass(email:String, password:String, home:()->Unit){

        viewModelScope.launch{
try {


    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                val name = it.result.user?.email?.split('@')?.get(0)
                createUser(name)
                home()
                Log.d("Created", "Account created:: ${it.result}")

            } else {
                Log.d("LoginError", "Failed::")
            }
        }
}
catch (e:Exception){
    Log.d("Exception","$e")
}
    }
    }
    fun createUser(Username: String?){
        val userId = auth.currentUser?.uid
//        val user = mutableMapOf<String,Any>()
//        user["UserID"]= userId.toString()
//        user["Name"]= Username.toString()
         val newUser = ZUser(null,userId,Username.toString(),"","Keep growing slowly","Entrepreneur").toMap()

//       FirebaseFirestore.getInstance().collection("Users").add(user)
        FirebaseFirestore.getInstance().collection("Users").add(newUser)
    }

}
//618