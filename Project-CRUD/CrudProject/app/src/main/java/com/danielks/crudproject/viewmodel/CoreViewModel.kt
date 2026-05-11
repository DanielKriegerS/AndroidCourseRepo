package com.danielks.crudproject.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielks.crudproject.model.Address
import com.danielks.crudproject.model.coremodels.Contact
import com.danielks.crudproject.repository.CoreRepository
import com.danielks.crudproject.network.dto.AddressApiDto
import com.danielks.crudproject.network.dto.ContactApiDto
import kotlinx.coroutines.launch

class CoreViewModel : ViewModel() {

    private val repository = CoreRepository()

    var contacts by mutableStateOf<List<Contact>>(emptyList())
        private set

    var isSubmitting by mutableStateOf(false)
        private set

    var address by mutableStateOf<Address?>(null)
        private set

    var formError by mutableStateOf<String?>(null)
        private set

    var saveSucceeded by mutableStateOf(false)
        private set

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var birthday by mutableStateOf("")

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
            // Se quiser, você pode definir uma mensagem genérica também aqui
            // formError = "Ocorreu um erro. Tente novamente."
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

    fun updateAddress(newAddress: Address?) {
        address = newAddress
    }

    /** Monta um Contact a partir dos campos atuais do formulário */
    private fun buildContact(id: Int? = null): Contact {
        return Contact(
            id = id,
            name = name,
            email = email,
            phone = phone,
            birthday = birthday,
            address = address // <- opcional
        )
    }

    /** Mensagem genérica conforme você pediu */
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
        address = contact.address
    }

    /**
     * Update sem receber Contact por parâmetro.
     * A fonte da verdade são os states (name/email/...)
     */
    fun updateContact(id: Int) {
        clearFormError()

        saveSucceeded = false

        val updated = buildContact(id = id)

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

    fun clearAddress() {
        address = null
    }

    fun resetFields() {
        name = ""
        email = ""
        phone = ""
        birthday = ""
        address = null
        // opcional: também limpar erro quando limpar o form
        formError = null
    }

    private fun toApiDto(id: Int? = null) = ContactApiDto(
        id = id,
        name = name,
        email = email,
        phone = phone,
        birthday = birthday,
        address = address?.let {
            AddressApiDto(
                zipCode = it.zipCode.value,
                street = it.street,
                neighborhood = it.neighborhood,
                city = it.city,
                state = it.state,
                number = it.number
            )
        }
    )
}