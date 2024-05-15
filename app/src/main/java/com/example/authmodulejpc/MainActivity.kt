package com.example.authmodulejpc

import android.os.Bundle
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    Column {
                        Spacer(modifier = Modifier.size(50.dp))
                        PageName(
                            pageTitle = "Log in to Authy",
                            pageSubTitle = "Welcome back! Sign in using your social account or email to continue us",
                        )
                        InputFieldWithLabel(
                            label = "Email",
                            hintText = "Enter your email",
                            keyboard = KeyboardType.Email,
                        )
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
                                        color =  MaterialTheme.colorScheme.primary,),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),

                                ) {}
                                Box(modifier = Modifier.size(7.dp))
                                Text(
                                    text = "Remember Me",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                            Text(
                                text = "Forgot Password?",
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary,
                            )
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
            color = MaterialTheme.colorScheme.secondary,
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
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.size(15.dp))

        BasicTextField(
            value = textFieldValue,
            singleLine = true,
            onValueChange = { textFieldValue = it },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(0.dp)
                ) {
                    if (textFieldValue.text.isEmpty()) {
                        Text(
                            text = hintText,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize // Adjust font size as needed
                        )
                    }
                    innerTextField()
                }
            },

        )
    }
}
