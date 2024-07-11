package com.example.authmodulejpc.presentation.auth

import android.telecom.Call
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authmodulejpc.R
import com.example.authmodulejpc.api.UserApi
import com.example.authmodulejpc.data.model.RequestModel
import com.example.authmodulejpc.data.model.ResponseModel
import com.example.authmodulejpc.ui.components.InputFieldWithLabel
import com.example.authmodulejpc.ui.components.PageName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Composable
fun LoginScreen(
    onNavigateToDashboard: (Int) -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToSignUp: () -> Unit,
){
    var checked by remember { mutableStateOf(false) }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val profileState = remember { mutableStateOf<ResponseModel?>(null) }
    val errorState = remember { mutableStateOf<String?>(null) } 


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
            .padding(all = 24.dp)
    ) {
        Spacer(Modifier.weight(1f))
        Column{
            PageName(
                pageTitle = "Log in to Authy",
                pageSubTitle = "Welcome back! Sign in using your social account or email to continue us",
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row(
                modifier = Modifier
                    .width(184.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .border(
                            shape = CircleShape,
                            width = 1.dp,
                            color = Color.Black
                        )
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier = Modifier
                            .align(
                                Alignment.Center
                            )
                            .size(24.dp)
                            .padding(all = 1.dp),
                    )
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .border(
                            shape = CircleShape,
                            width = 1.dp,
                            color = Color.Black
                        )
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier = Modifier
                            .align(
                                Alignment.Center
                            )
                            .size(24.dp)
                            .padding(all = 2.dp),
                    )
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .border(
                            shape = CircleShape,
                            width = 1.dp,
                            color = Color.Black
                        )
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.apple),
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier = Modifier
                            .align(
                                Alignment.Center
                            )
                            .size(24.dp)
                            .padding(all = 2.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.size(30.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )

                Text(
                    text = "OR",
                    Modifier.padding(
                        horizontal = 15.dp,
                    )
                )

                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        Column {
            InputFieldWithLabel(
                label = "Email",
                hintText = "",
                textFieldValue = email,
            )
            Box(modifier = Modifier.size(30.dp))


            InputFieldWithLabel(
                label = "Password",
                hintText = "",
                textFieldValue = password,
            )

            Box(modifier = Modifier.size(10.dp))
            errorState.value?.let {
            Text(text = it, color = androidx.compose.ui.graphics.Color.Red)
        }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = {
                            checked = !checked
                        },
                        modifier = Modifier.size(16.dp),
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color(0xFF24786D),
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray.copy(
                                alpha = 0.1f
                            )
                        ),

                        ) {}
                    Box(modifier = Modifier.size(7.dp))
                    Text(
                        text = "Remember Me",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color(0xFF24786D),
                    )
                }
                Text(
                    text = "Forgot Password?",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0xFF24786D),
                    modifier = Modifier.clickable {
                        onNavigateToForgotPassword();
                    }
                )
            }

        }
        Spacer(Modifier.weight(1f))
        Column {
            Button(
                onClick =
                {
                    Log.d("LoginScreen", "Email: ${email.value}, Password: ${password.value}");
                    sendRequest(
                        email = email.value, 
                        password = password.value, 
                        profileState = profileState
                    );


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF24786D)
                ),
            ) {
                Text(
                    text = "Login",
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Don’t have any account? Sign Up",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color(0xFF24786D),
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        onNavigateToSignUp()
                    }
            )
        }
    }

}


fun sendRequest(
    email : String,
    password : String,
    profileState: MutableState<ResponseModel?>,
) {
    Log.d("Login", "sendRequest: $email");

    val retrofit = Retrofit.Builder()
        .baseUrl("http://34.72.136.54:4067/api/v1/auth/login")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApi::class.java)

    val call = api.login(
         RequestModel(
            email = email,
            password = password,
            OS = "OS",
            model = "model1",
            FCMToken = "FCMToken"
        )
    )

    call.enqueue(object : retrofit2.Callback<ResponseModel> {
        override fun onResponse(
            call: retrofit2.Call<ResponseModel>,
            response: retrofit2.Response<ResponseModel>
        ) {
            if (response.isSuccessful) {
                profileState.value = response.body()!!
                Log.d("Login", "Success: ${response.body()?.user?.firstname}")
            } else {
                Log.d("Login", "Error: ${response.message()}")
            }
        }

        override fun onFailure(call: retrofit2.Call<ResponseModel>, t: Throwable) {
            Log.d("Login", "Failure: ${t.message}")
        }
    })


}