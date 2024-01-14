package com.example.googlebooks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.googlebooks.ui.theme.GoogleBooksTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleBooksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val db = FirebaseFirestore.getInstance()
                    val user : MutableMap<String,Any> = HashMap()
                    user["Firstname"]= "Anurag Anand"
                    user["Lastname"]=   "Ojha"

                    db.collection("Users")
                        .add(user)
                        .addOnSuccessListener {
                            Log.d("TAG","Success")
                        }
                        .addOnFailureListener {
                            Log.d("TAG","Not uploaded")
                        }
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoogleBooksTheme {
//        val b = Firebase
        Greeting("Android")
    }
}
//1234