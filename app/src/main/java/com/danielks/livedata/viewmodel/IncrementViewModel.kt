package com.danielks.livedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.danielks.livedata.model.IncrementModel

class IncrementViewModel {
    private val _obj = MutableLiveData(IncrementModel())
    val obj : LiveData<IncrementModel> = _obj

    fun incrementCounter() {
        val currentObj = _obj.value ?: IncrementModel()
        _obj.value = currentObj.copy(counter = currentObj.counter + 1)
    }
}