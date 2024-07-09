package com.example.authmodulejpc.data.api

import com.example.authmodulejpc.data.model.RequestModel
import com.example.authmodulejpc.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    fun login(@Body requestModel: RequestModel): Call<ResponseModel>
}
