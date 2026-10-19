package com.example.guestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.guestapp.data.Guest
import com.example.guestapp.data.GuestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GuestViewModel(private val repository: GuestRepository)
    : ViewModel() {

    // variavel interna
    private val _guests = MutableStateFlow<List<Guest>>(emptyList())

    //vai ser acessada na tela
    val guests: StateFlow<List<Guest>> = _guests.asStateFlow()

    init {
        getAllGuests()
    }

    fun getAllGuests() {
        val guests = repository.getAllGuests()
        _guests.value = guests
    }
    fun insertGuest(guest: Guest) {
        repository.insertGuest(guest)
        getAllGuests()
    }
    fun updateGuest(guest: Guest) {
        repository.updateGuest(guest)
    }
    fun deleteGuest(guest: Guest) {
        repository.deleteGuest(guest.id)
        getAllGuests()
    }
}