package com.example.cont2

class ContactRepository(private val contactDao: ContactDao) {
    val allContacts = contactDao.getAllContacts()

    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }

    suspend fun delete(contact: Contact) {
        contactDao.delete(contact)
    }
}
