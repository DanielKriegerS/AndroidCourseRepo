package com.danielks.crudproject.model

data class ZipCode(val value: String) {
    fun isValid(): Boolean = Regex("^[0-9]{8}$").matches(value)
}
