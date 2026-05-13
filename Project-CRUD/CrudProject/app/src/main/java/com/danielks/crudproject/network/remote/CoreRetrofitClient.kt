package com.danielks.crudproject.network.remote

import com.danielks.crudproject.network.service.CoreService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue
import kotlin.jvm.java  

object CoreRetrofitClient {
    // essas URL base poderiam ser variáveis de ambiente por segurança e para diferentes perfis (dev, prod,..)
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val CoreApi: CoreService by lazy {

        // Builder do Retrofit → usado para configurar o cliente
        Retrofit.Builder()

            // Define a URL base da API
            .baseUrl(BASE_URL)

            // Define o conversor JSON → objeto Kotlin
            .addConverterFactory(GsonConverterFactory.create())

            // Cria a instância do Retrofit com as configs acima
            .build()

            // Cria a implementação da interface ApiService
            .create(CoreService::class.java)
    }

}