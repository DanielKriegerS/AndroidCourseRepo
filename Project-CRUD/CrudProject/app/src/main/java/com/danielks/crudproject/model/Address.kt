package com.danielks.crudproject.model

data class Address(
    val id: Int? = null,
    val zipCode: ZipCode,
    val street: String,
    val neighborhood: String,
    val city: String,
    val state: String,
    val number: String? = null
) {
    fun getAddressInfo() : String {
        return "${zipCode.value} * $street * $neighborhood * $city * $state * ${number.orEmpty()}"
    }
}