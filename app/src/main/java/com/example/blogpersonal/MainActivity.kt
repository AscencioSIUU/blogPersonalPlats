package com.example.blogpersonal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.blogpersonal.data.UserDataStore
import com.example.blogpersonal.ui.theme.BlogPersonalTheme
import com.example.blogpersonal.navigation.NavGraph
import com.google.firebase.auth.FirebaseAuth
import com.example.blogpersonal.navigation.Routes
import com.example.blogpersonal.screens.SaveDataScreen
import com.example.blogpersonal.screens.ShowDataScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDataStore = UserDataStore(this)
        enableEdgeToEdge()
        setContent {
            BlogPersonalTheme {
                //SharedStorageApp(modifier = Modifier.fillMaxSize(),
                //    userDataStore = userDataStore)
                BlogApp()
            }
        }
    }
}

@Composable
fun BlogApp() {
    val navController = rememberNavController()
    val startDestination = if (FirebaseAuth.getInstance().currentUser == null) {
        Routes.LOGIN
    } else {
        Routes.SAVEDATA
    }

    NavGraph(navController = navController, startDestination = startDestination)
}

/*
@Composable
fun SharedStorageApp(modifier: Modifier = Modifier, userDataStore:
UserDataStore){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHostContainer(navController = navController, modifier =
        Modifier.padding(innerPadding), userDataStore = userDataStore)
    }
}
@Composable
fun NavHostContainer(navController: NavHostController, modifier: Modifier =
    Modifier, userDataStore: UserDataStore){
    NavHost(navController = navController, startDestination =
    Screen.Home.route, modifier = modifier){
        composable(Screen.Home.route) { SaveDataScreen(userDataStore =
        userDataStore) }
        composable(Screen.Profile.route) { ShowDataScreen(userDataStore =
        userDataStore) }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Profile,
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription =
                screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
// Evitar recrear el back stack al cambiar de pantalla
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
 */