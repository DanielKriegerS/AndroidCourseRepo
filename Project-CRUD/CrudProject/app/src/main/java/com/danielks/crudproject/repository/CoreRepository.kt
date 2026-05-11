package com.danielks.crudproject.repository

import com.danielks.crudproject.model.coremodels.Contact
import com.danielks.crudproject.network.remote.CoreRetrofitClient

class CoreRepository {
    suspend fun getContacts(): List<Contact> {
        return CoreRetrofitClient.CoreApi.getContacts()
    }

    suspend fun getContact(id: Int): Contact {
        return CoreRetrofitClient.CoreApi.getContactById(id)
    }

    suspend fun createContact(contact: Contact): Contact {
        return CoreRetrofitClient.CoreApi.createContact(contact)
    }

    suspend fun updateContact(id: Int, contact: Contact): Contact {
        return CoreRetrofitClient.CoreApi.updateContact(id, contact)
    }

    suspend fun deleteContact(id: Int) {
        CoreRetrofitClient.CoreApi.deleteContact(id)
    }
}