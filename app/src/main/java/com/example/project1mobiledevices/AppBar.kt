package com.example.project1mobiledevices

import android.graphics.Paint
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

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