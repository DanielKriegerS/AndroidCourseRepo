package com.danielks.crudproject.model.mappers

import com.danielks.crudproject.model.Address
import com.danielks.crudproject.model.ZipCode
import com.danielks.crudproject.model.coremodels.Contact
import com.danielks.crudproject.network.dto.AddressApiDto
import com.danielks.crudproject.network.dto.ContactApiDto

fun Address.toApiDto() = AddressApiDto(
    zipCode = zipCode.value, // <- STRING! (resolve o erro do Spring)
    street = street,
    neighborhood = neighborhood,
    city = city,
    state = state,
    number = number
)

fun Contact.toApiDto() = ContactApiDto(
    id = id,
    name = name,
    email = email,
    phone = phone,
    birthday = birthday,
    address = address?.toApiDto()
)

fun AddressApiDto.toDomain() = Address(
    zipCode = ZipCode(zipCode),
    street = street,
    neighborhood = neighborhood,
    city = city,
    state = state,
    number = number
)

fun ContactApiDto.toDomain() = Contact(
    id = id,
    name = name,
    email = email,
    phone = phone,
    birthday = birthday,
    address = address?.toDomain()
)