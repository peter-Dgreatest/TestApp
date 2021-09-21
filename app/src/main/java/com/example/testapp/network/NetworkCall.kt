package com.example.testapp.network

import com.example.testapp.domain.NetworkCountriesContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface NetworkCall {

    @FormUrlEncoded
    @POST("getCountries/")
    fun getCountries(@Field("name") name: String,) :Deferred<NetworkCountriesContainer>
}