package com.example.booklibraryapplication.model

// Data class

data class Book (
    val id: Int,
    val title: String,
    val author: String,
    val genre: String,
    val isRead: Boolean = false
)