package com.ykyahwa.kotlinstudy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_city.view.*

/**
 * Created by ehlee on 2018. 4. 10..
 */
class CityAdapter : RecyclerView.Adapter<CityAdapter.Holder>() {

    private val cities = listOf("Seoul" to "SEO",
            "Tokyo" to "TOK",
            "Mountain View" to "MTV",
            "Singapore" to "SIN",
            "New York" to "NYC")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(parent)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val (city, code) = cities[position]

        with (holder.itemView) {
            tv_city_name.text = city
            tv_city_code.text = code
        }
    }

    override fun getItemCount() = cities.size

    inner class Holder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false))

}
