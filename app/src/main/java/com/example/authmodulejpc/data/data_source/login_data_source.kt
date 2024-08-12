// UserApi.kt
package com.example.authmodulejpc.api

import com.example.authmodulejpc.data.data_source.LoginResponseDataModel
import com.example.authmodulejpc.data.model.RequestModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    fun login(@Body requestModel: RequestModel): Call<LoginResponseDataModel>
}
