package com.walmart.countrylistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameRegionCodeText: TextView = itemView.findViewById(R.id.nameRegionCodeText)
        val capitalText: TextView = itemView.findViewById(R.id.capitalText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.nameRegionCodeText.text = "${country.name}, ${country.region}     ${country.code}"
        holder.capitalText.text = country.capital
    }

    override fun getItemCount(): Int = countries.size
}
