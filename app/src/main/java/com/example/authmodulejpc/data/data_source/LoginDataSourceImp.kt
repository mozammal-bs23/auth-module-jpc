// LoginScreen.kt
package com.example.authmodulejpc.data.data_source

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.authmodulejpc.api.RestClient
import com.example.authmodulejpc.data.model.RequestModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

fun sendRequest(
    email: String,
    password: String,
    profileState: MutableState<LoginResponseDataModel?>,
    errorState: MutableState<String?> // Add this state to capture error messages
) {
    Timber.tag("Login").d("sendRequest: %s", email)

    val call = RestClient.api.login(
        RequestModel(
            email = email,
            password = password,
            OS = "OS",
            model = "model1",
            FCMToken = "FCMToken"
        )
    )

    call.enqueue(object : Callback<LoginResponseDataModel> {
        override fun onResponse(call: Call<LoginResponseDataModel>, response: Response<LoginResponseDataModel>) {
            if (response.isSuccessful) {
                profileState.value = response.body()
                errorState.value = null // Clear any previous error messages
                Timber.tag("Login").d("Success: %s", response.body()?.user?.firstname)
            } else {
                val errorBody = response.errorBody()?.string()
                errorState.value = errorBody ?: "Unknown error occurred"
                Timber.tag("Login").d("Error: %s", response.message())
            }
        }

        override fun onFailure(call: Call<LoginResponseDataModel>, t: Throwable) {
            errorState.value = t.message ?: "Network error occurred"
            Timber.tag("Login").d("Failure: %s", t.message)
        }
    })
}
