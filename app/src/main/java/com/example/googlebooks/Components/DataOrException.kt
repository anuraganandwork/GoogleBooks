package com.example.googlebooks.Components

data class DataOrException<T,Boolean,Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    val e: Exception? = null
)

