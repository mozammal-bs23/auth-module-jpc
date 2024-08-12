package com.example.authmodulejpc.data.data_source

data class ErrorResponseModel(
    val errorMessages: List<ErrorMessage>,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)