package com.test.samplegooglemaptab.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.samplegooglemaptab.api.College
import com.test.samplegooglemaptab.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MapViewModel : ViewModel() {
    private val _colleges = MutableStateFlow<List<College>>(emptyList())
    val colleges: StateFlow<List<College>> = _colleges

    init {
        fetchColleges()
    }

    private fun fetchColleges() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getNearbyColleges()
                _colleges.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}