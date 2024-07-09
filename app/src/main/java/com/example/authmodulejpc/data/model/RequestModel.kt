package com.example.authmodulejpc.data.model

data class RequestModel(
    val email: String,
    val password: String,
    val OS: String,
    val model: String,
    val FCMToken: String,
)
