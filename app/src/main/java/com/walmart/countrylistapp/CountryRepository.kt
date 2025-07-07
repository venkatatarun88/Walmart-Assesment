package com.walmart.countrylistapp

class CountryRepository {
    private val service = RetrofitClient.countryService

    suspend fun fetchCountries(): Result<List<Country>> {
        return try {
            val countries = service.getCountries()
            Result.success(countries)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
