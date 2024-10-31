package com.example.blogpersonal.model

import com.google.firebase.Timestamp

data class Post(
    val title: String = "",
    val content: String = "",
    val userId: String = "",
    val timestamp: Timestamp? = null
)