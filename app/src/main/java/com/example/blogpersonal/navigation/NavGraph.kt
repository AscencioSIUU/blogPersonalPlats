package com.example.blogpersonal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blogpersonal.data.UserDataStore
import com.example.blogpersonal.navigation.Routes
import com.example.blogpersonal.screens.LoginScreen
import com.example.blogpersonal.screens.SignUpScreen
import com.example.blogpersonal.screens.SaveDataScreen

@Composable
fun NavGraph(navController: NavHostController, startDestination: String) {
    val context = LocalContext.current
    val userDataStore = UserDataStore(context)

    NavHost(navController = navController, startDestination = startDestination) {
        // RUTA DE LOGIN
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Routes.SAVEDATA) },
                onNavigateToSignUp = { navController.navigate(Routes.SIGNUP) }  // Navega a SignUp
            )
        }

        // RUTA DE SIGNUP
        composable(Routes.SIGNUP) {
            SignUpScreen(
                onSignUpSuccess = { navController.navigate(Routes.SAVEDATA) },
                onNavigateToLogin = { navController.navigate(Routes.LOGIN) }
            )
        }

        // RUTA DE SAVE DATA SCREEN
        composable(Routes.SAVEDATA) {
            SaveDataScreen(
                userDataStore = userDataStore, // Pasa la instancia de userDataStore
                onLogout = { navController.navigate(Routes.LOGIN) }
            )
        }
    }
}

