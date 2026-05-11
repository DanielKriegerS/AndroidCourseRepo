package com.danielks.crudproject.network.service

import com.danielks.crudproject.model.coremodels.Contact
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CoreService {
    @GET("contacts")
    suspend fun getContacts(): List<Contact>

    @POST("contacts")
    suspend fun createContact(@Body contact: Contact): Contact

    @GET("contacts/{id}")
    suspend fun getContactById(@Path("id") id: Int): Contact

    @PUT("contacts/{id}")
    suspend fun updateContact(@Path("id") id: Int, @Body contact: Contact): Contact

    @DELETE("contacts/{id}")
    suspend fun deleteContact(@Path("id") id: Int)
}