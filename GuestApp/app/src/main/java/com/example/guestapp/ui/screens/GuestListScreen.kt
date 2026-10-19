package com.example.guestapp.ui.screens

import GuestDatabaseHelper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guestapp.data.Guest
import com.example.guestapp.data.GuestRepository
import com.example.guestapp.data.GuestRepositorySQLiteImpl
import com.example.guestapp.ui.components.DrawerHeader
import com.example.guestapp.ui.components.DrawerMenu
import com.example.guestapp.viewmodel.GuestViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuestListScreen(
    navController: NavController
) {
    val context = LocalContext.current

    val dbHelper = GuestDatabaseHelper(context)
    val repository = GuestRepositorySQLiteImpl(dbHelper)
    val viewmodel = GuestViewModel(repository)

    val guestList by viewmodel.guests.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    var guestToEdit by remember { mutableStateOf<Guest?>(null) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                Modifier.fillMaxWidth(0.8f)
            ) {
                DrawerHeader()
                DrawerMenu{
                    route -> navController.navigate(route)
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Lista de convidados") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {drawerState.open()}
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {navController.navigate("ProfileScreen")}) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Open")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    showDialog = true
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar convidado")
                }
            }
        )
        { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding)
            ) {
                items(guestList.size) { index ->
                    GuestCard(guestList[index],
                        onEdit = {
                            showDialog = true
                            guestToEdit = guestList[index]
                        }, onDelete = {
                            viewmodel.deleteGuest(guestList[index])
                        })
                }
            }
        }

    }


    //LAMBDA function
    GuestDialogForm(
        isOpen = showDialog,
        onDismiss = {
            showDialog = false
        },
        onSave = {
            guest ->
            if (guestToEdit == null) {
                viewmodel.insertGuest(guest)
            } else {
                viewmodel.updateGuest(guest)
            }

        },
        guest = guestToEdit
    )
}

@Composable
fun GuestCard(guest: Guest, onEdit: (Guest) -> Unit, onDelete: (Guest) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(guest.name)
                Text(guest.email)
                Text(guest.phone)
            }
            Row {
                IconButton(onClick = {
                    onEdit(guest)
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Alterar")
                }
                IconButton(onClick = {
                    onDelete(guest)
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Excluir")
                }
            }
        }
    }
}