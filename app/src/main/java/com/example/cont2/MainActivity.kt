package com.example.cont2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ContactViewModel
    private lateinit var contactAdapter: ContactAdapter

    private lateinit var lastNameEditText: EditText
    private lateinit var initialsEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var contactsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        lastNameEditText = findViewById(R.id.lastNameEditText)
        initialsEditText = findViewById(R.id.initialsEditText)
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)
        saveButton = findViewById(R.id.saveButton)
        contactsRecyclerView = findViewById(R.id.contactsRecyclerView)

        setupRecyclerView()
        setupSaveButton()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        contactAdapter = ContactAdapter(
            onDeleteClick = { contact -> viewModel.delete(contact) },
            onItemClick = {  }
        )
        contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        contactsRecyclerView.adapter = contactAdapter

        viewModel.allContacts.observe(this) { contacts ->
            contactAdapter.submitList(contacts)
        }
    }

    private fun setupSaveButton() {
        saveButton.setOnClickListener {
            val lastName = lastNameEditText.text.toString()
            val initials = initialsEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()

            if (lastName.isNotBlank() && initials.isNotBlank() && phoneNumber.isNotBlank()) {
                val contact = Contact(
                    lastName = lastName,
                    initials = initials,
                    phoneNumber = phoneNumber
                )
                viewModel.insert(contact)


                lastNameEditText.text.clear()
                initialsEditText.text.clear()
                phoneNumberEditText.text.clear()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}