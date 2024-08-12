package com.example.authmodulejpc.data.data_source

data class LoginRequestDataModel(
    val FCMToken: String,
    val OS: String,
    val email: String,
    val model: String,
    val password: String
)