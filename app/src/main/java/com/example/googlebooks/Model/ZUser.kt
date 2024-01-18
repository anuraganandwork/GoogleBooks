package com.example.googlebooks.Model

data class ZUser(val id: String?,
                 val userId: String?,
                 val name: String,
                 val avatarUrl : String,
                 val quote: String,
                 val proffesion :String){

    fun toMap() : MutableMap<String, String?> {
       return mutableMapOf(
           "user_id" to this.userId,
           "user_name" to this.name,
           "avatarUrl" to this.avatarUrl,
           "quote" to this.quote,
           "proffesion" to this.proffesion

       )
    }
}
