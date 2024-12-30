package com.example.cont2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert
    suspend fun insert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contacts ORDER BY createdAt DESC")
    fun getAllContacts(): Flow<List<Contact>>
}
