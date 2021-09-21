package com.example.testapp.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class CountriesModel(
    val name : String,
    val phonecode : String
)

@JsonClass(generateAdapter = true)
data class NetworkCountriesContainer(val countries: List<NetworkCountries>)


/**
 *
 */
@JsonClass(generateAdapter = true)
data class NetworkCountries(
    val name: String,
    val phonecode : String)

fun NetworkCountriesContainer.asDomainModel(): List<CountriesModel> {
    return countries.map {
        CountriesModel ( name = it.name,
            phonecode = it.phonecode)
    }.toList()
}
