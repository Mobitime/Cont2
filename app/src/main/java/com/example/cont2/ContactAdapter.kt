package com.example.cont2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val onDeleteClick: (Contact) -> Unit,
    private val onItemClick: (Contact) -> Unit
) : ListAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_list_item, parent, false)
        return ContactViewHolder(view, onDeleteClick, onItemClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ContactViewHolder(
        itemView: View,
        private val onDeleteClick: (Contact) -> Unit,
        private val onItemClick: (Contact) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)
        private val initialsTextView: TextView = itemView.findViewById(R.id.initialsTextView)
        private val phoneNumberTextView: TextView = itemView.findViewById(R.id.phoneNumberTextView)
        private val createdAtTextView: TextView = itemView.findViewById(R.id.createdAtTextView)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        fun bind(contact: Contact) {
            lastNameTextView.text = contact.lastName
            initialsTextView.text = contact.initials
            phoneNumberTextView.text = contact.phoneNumber
            createdAtTextView.text = contact.getFormattedCreatedAt()

            deleteButton.setOnClickListener { onDeleteClick(contact) }
            itemView.setOnClickListener { onItemClick(contact) }
        }
    }

    class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}
