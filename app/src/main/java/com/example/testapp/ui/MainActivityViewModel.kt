package com.example.testapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testapp.domain.CountriesModel
import com.example.testapp.repository.NameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application){


    val nameRepository = NameRepository(application)



    private val viewModelJob = SupervisorJob()



    var countries  = MutableLiveData<List<CountriesModel>>()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getNames(queryString :String){
        viewModelScope.launch {
            countries.value = nameRepository.getNames(queryString)
        }
    }





}