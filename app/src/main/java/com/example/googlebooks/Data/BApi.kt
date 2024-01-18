package com.example.googlebooks.Data

data class BApi(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)