package com.test.samplegooglemaptab

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.test.samplegooglemaptab.screens.HomeScreen
import com.test.samplegooglemaptab.screens.MapScreen
import com.test.samplegooglemaptab.ui.theme.SampleGoogleMapTabTheme
import com.test.samplegooglemaptab.utilities.Routes.HOME_ROUTE
import com.test.samplegooglemaptab.utilities.Routes.MAP_ROUTE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            myApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    myApp()
}

@Composable
fun myApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    BackHandler(
        enabled = currentRoute == MAP_ROUTE
    ) {
        navController.navigate(HOME_ROUTE) {
            popUpTo(HOME_ROUTE)
            launchSingleTop = true
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == HOME_ROUTE,
                    onClick = {
                        navController.navigate(HOME_ROUTE) {
                            popUpTo(HOME_ROUTE)
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    },
                    label = {
                        Text("Home")
                    }
                )
                NavigationBarItem(
                    selected = currentRoute == MAP_ROUTE,
                    onClick = {
                        navController.navigate(MAP_ROUTE) {
                            popUpTo(HOME_ROUTE)
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(Icons.Default.LocationOn, contentDescription = "Map")
                    },
                    label = {
                        Text("Map")
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HOME_ROUTE,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(HOME_ROUTE) {
                HomeScreen()
            }

            composable(MAP_ROUTE) {
                MapScreen()
            }
        }
    }

    @Composable
    fun Box() {}
}