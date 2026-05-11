package com.danielks.crudproject.model.coremodels

import com.danielks.crudproject.model.Address

data class Contact (
    val id: Int? = null,
    var name: String,
    // em implementação mais robusta outros campos poderiam ser classes; principalmente e-mail e telefone
    var email: String,
    var phone: String,
    var birthday: String,
    var address: Address? = null
) {
    fun isValid(): Boolean {
        return validateContactSimpleFields()
    }

    private fun validateContactSimpleFields(): Boolean {
        val emailIsValid = email.isValidEmail()
        val phoneIsValid = phone.isValidPhone()
        val nameIsValid = name.length >= 3

        var listOfFields = listOf(email, phone, name, birthday)

        var allFieldsNotBlank = validateNotBlankFields(listOfFields)

        return allFieldsNotBlank &&
                emailIsValid &&
                phoneIsValid &&
                nameIsValid
    }

    private fun validateNotBlankFields(fields: List<String>) = fields.all { it.isNotBlank() }

}
private fun String.isValidEmail() : Boolean {
    val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    return emailRegex.matches(this)
}

private fun String.isValidPhone() : Boolean {
    val phoneRegex = Regex("^[0-9]{10,11}$")
    return phoneRegex.matches(this)
}


