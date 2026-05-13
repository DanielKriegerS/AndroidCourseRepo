package com.danielks.crudproject.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielks.crudproject.model.Address
import com.danielks.crudproject.model.coremodels.Contact
import com.danielks.crudproject.repository.CoreRepository
import kotlinx.coroutines.launch

class CoreViewModel : ViewModel() {

    private val repository = CoreRepository()


    var contacts by mutableStateOf<List<Contact>>(emptyList())
        private set
    var isSubmitting by mutableStateOf(false)
        private set
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var birthday by mutableStateOf("")

    private var _address by mutableStateOf<Address?>(null)
    val address: Address? get() = _address

    var formError by mutableStateOf<String?>(null)
        private set

    var saveSucceeded by mutableStateOf(false)
        private set


    fun setAddress(newAddress: Address?) {
        _address = newAddress
    }

    fun clearAddress() {
        _address = null
    }

    fun updateAddressNumber(newNumber: String) {
        _address = _address?.copy(number = newNumber)
    }


    init {
        fetchContacts()
    }

    fun consumeSaveSucceeded() {
        saveSucceeded = false
    }

    private fun launchSubmitting(block: suspend () -> Unit) = viewModelScope.launch {
        isSubmitting = true
        try {
            block()
        } catch (e: Exception) {
            e.printStackTrace()
            formError = "Ocorreu um erro. Tente novamente."
        } finally {
            isSubmitting = false
        }
    }

    private suspend fun refreshContacts() {
        contacts = repository.getContacts()
    }

    fun fetchContacts() = launchSubmitting {
        refreshContacts()
    }

    private fun buildContact(id: Int? = null): Contact =
        Contact(
            id = id,
            name = name,
            email = email,
            phone = phone,
            birthday = birthday,
            address = _address
        )

    private fun setGenericFormError() {
        formError = "Verifique os dados informados."
    }

    fun clearFormError() {
        formError = null
    }

    fun addContact() {
        clearFormError()
        saveSucceeded = false
        val newContact = buildContact()

        if (!newContact.isValid()) {
            setGenericFormError()
            return
        }

        launchSubmitting {
            repository.createContact(newContact)
            resetFields()
            refreshContacts()
            saveSucceeded = true
        }
    }

    fun getContact(id: Int) = launchSubmitting {
        clearFormError()

        val contact = repository.getContact(id)
        name = contact.name
        email = contact.email
        phone = contact.phone
        birthday = contact.birthday
        _address = contact.address
    }

    fun updateContact(id: Int) {
        clearFormError()

        saveSucceeded = false

        val updated = buildContact(id)

        if (!updated.isValid()) {
            setGenericFormError()
            return
        }

        launchSubmitting {
            repository.updateContact(id, updated)
            resetFields()
            refreshContacts()
            saveSucceeded = true
        }
    }

    fun deleteContact(id: Int?) = launchSubmitting {
        clearFormError()

        if (id == null) return@launchSubmitting

        repository.deleteContact(id)
        refreshContacts()
    }

    fun resetFields() {
        name = ""
        email = ""
        phone = ""
        birthday = ""
        _address = null
        // opcional: também limpar erro quando limpar o form
        formError = null
    }
}