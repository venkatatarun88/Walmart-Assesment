package com.walmart.countrylistapp

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val repository = CountryRepository()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadCountries() {
        viewModelScope.launch {
            val result = repository.fetchCountries()
            if (result.isSuccess) {
                _countries.postValue(result.getOrNull())
                _error.postValue(null)
            } else {
                _countries.postValue(emptyList())
                _error.postValue(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}
