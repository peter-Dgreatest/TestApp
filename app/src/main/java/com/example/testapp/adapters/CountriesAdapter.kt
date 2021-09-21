package com.example.testapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.CountryloaderBinding
import com.example.testapp.domain.CountriesModel

class CountriesAdapter(val clickListener: CountryClickListener):
    ListAdapter<CountriesModel, RecyclerView.ViewHolder>(LessonDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CountriesModelViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = getItem(position)
        var vHolder = (holder as CountriesModelViewHolder)!!
                vHolder.bind(item, clickListener)

    }

}

class CountriesModelViewHolder private constructor(val binding: CountryloaderBinding) :
    RecyclerView.ViewHolder(
        binding.root
    ) {


    fun bind(item: CountriesModel, clickListener: CountryClickListener) {
        binding.country = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CountriesModelViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CountryloaderBinding.inflate(layoutInflater, parent, false)
            return CountriesModelViewHolder(binding)
        }
    }
}


class  LessonDiffCallBack: DiffUtil.ItemCallback<CountriesModel>(){
    override fun areItemsTheSame(oldItem: CountriesModel, newItem: CountriesModel): Boolean {
        return oldItem.name==newItem.name
    }

    override fun areContentsTheSame(oldItem: CountriesModel, newItem: CountriesModel): Boolean {
        return oldItem==newItem
    }

}

class CountryClickListener(val clickListener: (countryName: String) -> Unit){
    fun onClick(country: CountriesModel) = clickListener(country.name)
}