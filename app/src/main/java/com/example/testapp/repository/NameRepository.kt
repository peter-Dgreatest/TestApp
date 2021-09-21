package com.example.testapp.repository

import android.app.Application
import com.example.msalehstoreapp.network.Network
import com.example.msalehstoreapp.network.helpers.SafeApiCall
import com.example.testapp.domain.CountriesModel
import com.example.testapp.domain.asDomainModel

class NameRepository(val application: Application): SafeApiCall {

    suspend fun getNames(queryName:String) = safeApiCall  {
        val info = Network(application.applicationContext).mnetworks.getCountries(queryName).await()
        info.asDomainModel()
    }
}