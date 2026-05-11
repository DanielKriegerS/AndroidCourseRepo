package com.danielks.crudproject.network.dto

data class AddressApiDto(
    val zipCode: String,
    val street: String,
    val neighborhood: String,
    val city: String,
    val state: String,
    val number: String? = null
)
