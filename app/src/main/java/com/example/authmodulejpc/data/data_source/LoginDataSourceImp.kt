// LoginScreen.kt
package com.example.authmodulejpc.presentation.auth

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.authmodulejpc.api.RestClient
import com.example.authmodulejpc.data.model.RequestModel
import com.example.authmodulejpc.data.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun sendRequest(
    email: String,
    password: String,
    profileState: MutableState<ResponseModel?>,
    errorState: MutableState<String?> // Add this state to capture error messages
) {
    Log.d("Login", "sendRequest: $email")

    val call = RestClient.api.login(
        RequestModel(
            email = email,
            password = password,
            OS = "OS",
            model = "model1",
            FCMToken = "FCMToken"
        )
    )

    call.enqueue(object : Callback<ResponseModel> {
        override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
            if (response.isSuccessful) {
                profileState.value = response.body()
                errorState.value = null // Clear any previous error messages
                Log.d("Login", "Success: ${response.body()?.user?.firstname}")
            } else {
                val errorBody = response.errorBody()?.string()
                errorState.value = errorBody ?: "Unknown error occurred"
                Log.d("Login", "Error: ${response.message()}")
            }
        }

        override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
            errorState.value = t.message ?: "Network error occurred"
            Log.d("Login", "Failure: ${t.message}")
        }
    })
}
