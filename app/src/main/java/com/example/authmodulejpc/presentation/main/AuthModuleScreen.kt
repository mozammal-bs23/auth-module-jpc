@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.authmodulejpc.presentation.main

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.authmodulejpc.R
import com.example.authmodulejpc.presentation.auth.ChangePasswordScreen
import com.example.authmodulejpc.presentation.auth.EmailConfirmationScreen
import com.example.authmodulejpc.presentation.auth.ForgotPasswordScreen
import com.example.authmodulejpc.presentation.auth.LoginScreen
import com.example.authmodulejpc.presentation.auth.ResetPasswordScreen
import com.example.authmodulejpc.presentation.auth.SignUpScreen
import androidx.navigation.compose.NavHost as NavHost

enum class AuthModuleScreen(@StringRes val title: Int) {
    Start(title = R.string.login),
    Signup(title = R.string.signup),
    Dashboard(title = R.string.dashboard),
    ForgotPassword(title = R.string.forgot_password),
    ResetPassword(title = R.string.reset_password),
    UpdateProfile(title = R.string.update_profile),
    ChangePassword(title = R.string.change_password),
    OTP(title = R.string.otp_sent),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthModuleAppBar(
    currentScreen: AuthModuleScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}



@Composable
fun AuthModuleApp(
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AuthModuleScreen.valueOf(
        backStackEntry?.destination?.route ?: AuthModuleScreen.Start.name
    )

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AuthModuleScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(innerPadding)
        ) {
            composable(route = AuthModuleScreen.Start.name) {
                LoginScreen(
                    onNavigateToSignUp = {
                        navController.navigate(AuthModuleScreen.Signup.name)
                    },
                    onNavigateToForgotPassword = {
                        navController.navigate(AuthModuleScreen.ForgotPassword.name)
                    },
                    onNavigateToDashboard = {
                        navController.navigate(AuthModuleScreen.Dashboard.name)
                    }
                )
            }

            composable(route = AuthModuleScreen.Signup.name) {
                SignUpScreen(
                    onNavigateToLogin = {
                        navController.navigate(AuthModuleScreen.Start.name)
                    },
                    onNavigateToOTP = {
                        navController.navigate(AuthModuleScreen.OTP.name)
                    }
                )
            }

            composable(route = AuthModuleScreen.ForgotPassword.name) {
                ForgotPasswordScreen()
            }

            composable(route = AuthModuleScreen.OTP.name) {
                EmailConfirmationScreen()
            }

            composable(route = AuthModuleScreen.ResetPassword.name) {
                ResetPasswordScreen()
            }

            composable(route = AuthModuleScreen.ChangePassword.name) {
                ChangePasswordScreen()
            }
        }

    }

}