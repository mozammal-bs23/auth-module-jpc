// RestClient.kt
package com.example.authmodulejpc.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private const val BASE_URL = "http://34.72.136.54:4067/api/v1/auth/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: UserApi = retrofit.create(UserApi::class.java)
}




