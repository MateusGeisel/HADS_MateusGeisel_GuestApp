package com.example.guestapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DrawerHeader() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = (Alignment.CenterHorizontally)) {
        Text("Menu")

    }
}

@Composable
fun DrawerMenu(onOptionSelected: (String) -> Unit){
    Column {
        ListItem(
            leadingContent = { Icon(Icons.Filled.CheckCircle, contentDescription = "Menu convidados") },
            headlineContent = { Text("Convidados") },
            modifier = Modifier.padding(16.dp).clickable {
                onOptionSelected("GuestListScreen")
            }
        )
        ListItem(
            leadingContent = { Icon(Icons.Filled.AccountCircle, contentDescription = "Meu Perfil") },
            headlineContent = { Text("Perfil") },
            modifier = Modifier.padding(16.dp).clickable {
                onOptionSelected("ProfileScreen")
            }
        )

    }
}