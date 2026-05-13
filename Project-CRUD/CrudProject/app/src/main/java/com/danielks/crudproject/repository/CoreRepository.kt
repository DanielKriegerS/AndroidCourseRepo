package com.danielks.crudproject.repository

import com.danielks.crudproject.model.coremodels.Contact
import com.danielks.crudproject.model.mappers.toApiDto
import com.danielks.crudproject.model.mappers.toDomain
import com.danielks.crudproject.network.dto.ContactApiDto
import com.danielks.crudproject.network.remote.CoreRetrofitClient

class CoreRepository {
    suspend fun getContacts(): List<Contact> {
        return CoreRetrofitClient.CoreApi.getContacts().map { it.toDomain() }
    }

    suspend fun getContact(id: Int): Contact {
        return CoreRetrofitClient.CoreApi.getContactById(id).toDomain()
    }

    suspend fun createContact(contact: Contact): Contact {
        return CoreRetrofitClient.CoreApi.createContact(contact.toApiDto()).toDomain()
    }

    suspend fun updateContact(id: Int, contact: Contact): Contact {
        return CoreRetrofitClient.CoreApi.updateContact(id, contact.toApiDto()).toDomain()
    }

    suspend fun deleteContact(id: Int) {
        CoreRetrofitClient.CoreApi.deleteContact(id)
    }
}