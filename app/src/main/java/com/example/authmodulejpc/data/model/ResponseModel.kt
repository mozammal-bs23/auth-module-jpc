package com.example.authmodulejpc.data.model

data class ResponseModel(
    val token: String,
    val user: User,
    val message: String,
) {
}

data class User(
    val _id: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val role: String,
)
