package com.ykyahwa.kotlinstudy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by ehlee on 2018. 4. 10..
 */
class CityAdapter : RecyclerView.Adapter<CityAdapter.Holder>() {
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(parent)

    inner class Holder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false))

}
