package com.danielks.crudproject.network.dto

data class ContactApiDto(
    val id: Int? = null,
    val name: String,
    val email: String,
    val phone: String,
    val birthday: String,
    val address: AddressApiDto? = null
)
