package com.danielks.crudproject.repository

import com.danielks.crudproject.model.Address
import com.danielks.crudproject.model.ZipCode
import com.danielks.crudproject.network.remote.ZipCodeRetrofitClient

class ZipCodeRepository {

    suspend fun getAddressByZipCode(zipCode: ZipCode): Address {
        if (!zipCode.isValid()) {
            throw IllegalArgumentException("CEP inválido")
        }

        val dto = ZipCodeRetrofitClient.zipCodeApi.getZipCodeInfo(zipCode.value)


        if (dto.erro == true) {
            throw IllegalArgumentException("CEP não encontrado")
        }


        val cepDigits = dto.cep.orEmpty().filter { it.isDigit() }

        return Address(
            zipCode = ZipCode(cepDigits),
            street = dto.logradouro.orEmpty(),
            neighborhood = dto.bairro.orEmpty(),
            city = dto.localidade.orEmpty(),
            state = dto.uf.orEmpty(),
            number = null
        )
    }
}