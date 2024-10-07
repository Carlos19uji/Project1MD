package com.example.project1mobiledevices

import android.graphics.Paint
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun TopNavigationBar(
    title: String,
    onBackClick: () -> Unit    // Acción cuando se hace clic en el ícono de retroceso
) {
    TopAppBar(
        navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow",
                    modifier = Modifier.clickable(onClick = onBackClick),  // Hacemos que el ícono sea clicable
                    tint = Color.Blue   // Color del ícono
                )
            },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = title, color = Color.Blue,)
            }
        }
        )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.Yellow)
        ) {
            Column() {
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = "Players",
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.PlayerList.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                TextButton(
                    onClick = {
                        navController.navigate(Screen.PlayerList.route){
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Players")
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column() {
                Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = "Favourite",
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Favourites.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                TextButton(
                    onClick = {
                        navController.navigate(Screen.Favourites.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Favourites")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column() {
                    Icon(imageVector = Icons.Default.Info,
                        contentDescription = "Club_History",
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.Club_history.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                    TextButton(
                        onClick = {
                            navController.navigate(Screen.Club_history.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Club History")
                    }

                }
            }
        }
    }
}