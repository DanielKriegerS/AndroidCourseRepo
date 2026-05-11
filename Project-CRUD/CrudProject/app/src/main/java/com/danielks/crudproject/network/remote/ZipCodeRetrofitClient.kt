package com.danielks.crudproject.network.remote

import com.danielks.crudproject.network.service.ZipCodeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue
import kotlin.jvm.java

object ZipCodeRetrofitClient {

    // URL base da API (todas as requisições partem daqui)
    private const val BASE_URL = "https://viacep.com.br/ws/"

    // Instância da API criada de forma lazy (só quando for usada)
    val zipCodeApi: ZipCodeService by lazy {

        // Builder do Retrofit → usado para configurar o cliente
        Retrofit.Builder()

            // Define a URL base da API
            .baseUrl(BASE_URL)

            // Define o conversor JSON → objeto Kotlin
            .addConverterFactory(GsonConverterFactory.create())

            // Cria a instância do Retrofit com as configs acima
            .build()

            // Cria a implementação da interface ApiService
            .create(ZipCodeService::class.java)
    }

}