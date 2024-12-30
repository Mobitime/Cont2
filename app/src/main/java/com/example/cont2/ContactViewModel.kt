package com.example.cont2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val database = ContactDatabase.getDatabase(application)
    private val repository: ContactRepository

    val allContacts: LiveData<List<Contact>>

    init {
        val contactDao = database.contactDao()
        repository = ContactRepository(contactDao)
        allContacts = repository.allContacts.asLiveData()
    }

    fun insert(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
    }

    fun delete(contact: Contact) = viewModelScope.launch {
        repository.delete(contact)
    }
}
