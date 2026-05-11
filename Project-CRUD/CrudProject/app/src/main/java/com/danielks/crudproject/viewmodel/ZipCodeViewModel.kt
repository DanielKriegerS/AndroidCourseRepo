package com.danielks.crudproject.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielks.crudproject.model.Address
import com.danielks.crudproject.model.ZipCode
import com.danielks.crudproject.repository.ZipCodeRepository
import kotlinx.coroutines.launch

class ZipCodeViewModel : ViewModel() {
    private val repository = ZipCodeRepository()

    var addressState by mutableStateOf<Address?>(null)
        private set

    var isSubmitting by mutableStateOf(false)
        private set

    var zipError by mutableStateOf<String?>(null)
        private set

    fun getAddressByZipCode(zipCode: ZipCode) {
        viewModelScope.launch {
            isSubmitting = true
            try {
                addressState = null
                addressState = repository.getAddressByZipCode(zipCode)
            } catch (e: Exception) {
                zipError = "Falha ao buscar CEP."
                addressState = null
            } finally {
                isSubmitting = false
            }
        }
    }
}