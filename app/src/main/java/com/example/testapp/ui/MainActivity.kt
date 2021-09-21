package com.example.testapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.msalehstoreapp.network.helpers.Resource
import com.example.msalehstoreapp.network.helpers.Resource.*
import com.example.testapp.R
import com.example.testapp.adapters.CountriesAdapter
import com.example.testapp.adapters.CountryClickListener
import com.example.testapp.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.view.*
import com.example.testapp.util.visible


class MainActivity : AppCompatActivity() {


    private lateinit var countryViewModel : MainActivityViewModel
    private lateinit var countryAdapter: CountriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding  = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )

        countryViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        countryAdapter= CountriesAdapter(CountryClickListener { countryname ->
            run {
                val chip = Chip(this)
                chip.text = countryname
                chip.setChipBackgroundColorResource(R.color.colorAccent)
                chip.isCloseIconVisible = true

                binding.chipGroup.addView(chip)
            }
        })

        binding.rcyView.adapter= countryAdapter


        binding.btnSearch.setOnClickListener(View.OnClickListener {
            countryViewModel.getNames(binding.edtSearch.text.toString())
        })

        countryViewModel.countries.observe(this, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Success -> {
                    it?.apply {
                        countryAdapter.submitList(it.value)
                    }
                }
                is Resource.Failure -> Toast.makeText(
                    applicationContext,
                    "Error Occured",
                    Toast.LENGTH_LONG
                ).show()
            }
        })




        binding.viewmmodel = countryViewModel
    }
}