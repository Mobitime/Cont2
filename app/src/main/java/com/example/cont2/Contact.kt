package com.example.cont2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val lastName: String,
    val initials: String,
    val phoneNumber: String,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun getFormattedCreatedAt(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date(createdAt))
    }
}
