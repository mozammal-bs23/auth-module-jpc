package com.example.authmodulejpc

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.copy
import com.example.authmodulejpc.ui.theme.AuthModuleJPCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var checked by remember { mutableStateOf(false) }

            AuthModuleJPCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                ) {
                    Column (
                       verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(24.dp),
                    ){
                        Spacer(modifier = Modifier.size(101.dp))
                        PageName(
                            pageTitle = "Log in to Authy",
                            pageSubTitle = "Welcome back! Sign in using your social account or email to continue us",
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            InputFieldWithLabel(
                                label = "Email",
                                hintText = "Enter your email",
                                keyboard = KeyboardType.Email,
                            )
                            Box(modifier = Modifier.size(30.dp))


                            InputFieldWithLabel(
                                label = "Password",
                                hintText = "Enter your password",
                                keyboard = KeyboardType.Password,
                            )

                            Box(modifier = Modifier.size(10.dp))

                            Row (
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
                                        border = BorderStroke(width = 2.dp,
                                            color =  Color(0xFF24786D),),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.1f)),

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
                                )
                            }

                        }
                        Column {
                            Button(
                                onClick = { checked = !checked },
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
                            )

                            Spacer(Modifier.height(37.dp))
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun PageName(
    pageTitle: String,
    pageSubTitle: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = pageTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
        Text(
            text = pageSubTitle,
            color = Color.Gray.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            modifier =
            Modifier.padding(
                horizontal = 41.dp,
                vertical = 8.dp,
            ),
        )
    }
}


@Composable
fun InputFieldWithLabel(
    label: String,
    hintText: String,
    keyboard: KeyboardType,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text = "")) }

    Column(
        modifier = Modifier
            .padding(0.dp)
    ) {

        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color(0xFF24786D)
        )

        BasicTextField(
            value = textFieldValue,
            singleLine = true,
            onValueChange = { textFieldValue = it },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(60.dp)
                        .drawBehind {
                            drawLine(
                                color = Color.Gray.copy(alpha = 0.5f),
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                            )
                        }
                    ,
                ) {
                    if (textFieldValue.text.isEmpty()) {
                        Text(
                            text = hintText,
                            color = Color(0xFF24786D).copy(alpha = 0.2f),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 15.dp,
                                ),

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 15.dp,
                            ),
                    ) {
                        innerTextField()
                    }
                }
            },

        )
    }
}
