package com.example.testapp.repository

import android.app.Application
import com.example.msalehstoreapp.network.Network
import com.example.testapp.domain.CountriesModel
import com.example.testapp.domain.asDomainModel

class NameRepository(val application: Application){

    suspend fun getNames(queryName:String): List<CountriesModel> {
        val info = Network(application.applicationContext).mnetworks.getCountries(queryName).await()
        return info.asDomainModel()
    }
}