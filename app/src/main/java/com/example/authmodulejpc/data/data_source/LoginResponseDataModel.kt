package com.example.authmodulejpc.data.data_source

data class LoginResponseDataModel(
    val message: String,
    val token: String,
    val user: User
)