package com.example.guestapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.guestapp.data.Guest

@Composable
fun GuestDialogForm(
    isOpen: Boolean,
    onDismiss: () -> Unit,
    onSave: (Guest) -> Unit,
    guest: Guest? = null
) {
    if (isOpen) {
        var name by remember { mutableStateOf(guest?.name ?: "") }
        var email by remember { mutableStateOf(guest?.email ?: "") }
        var phone by remember { mutableStateOf(guest?.phone ?: "") }
        var confirmed by remember { mutableStateOf(guest?.confirmed == 1) }

        AlertDialog(
            title = { Text(text = if (guest == null) "Adicionar Convidado" else "Editar Convidado") },
            onDismissRequest = {},
            text = {
                Column {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nome") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Fone") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = confirmed,
                            onCheckedChange = { confirmed = it }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        BasicText("Confirmado no evento")
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val newGuest = Guest(
                            id = guest?.id ?: 0,
                            name = name,
                            email = email,
                            phone = phone,
                            confirmed = if (confirmed) 1 else 0
                        )
                        onSave(newGuest)
                        onDismiss()
                    }
                ) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
