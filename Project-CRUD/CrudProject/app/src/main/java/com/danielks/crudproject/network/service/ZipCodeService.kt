package com.danielks.crudproject.network.service

import com.danielks.crudproject.network.dto.ViaCepResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ZipCodeService {
    @GET("{zipCode}/json/")
    suspend fun getZipCodeInfo(@Path("zipCode") zipCode: String): ViaCepResponseDto
}