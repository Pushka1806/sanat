package com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.menuList.CustomRecyclerAdapter
import com.example.sanatoriy.bussiness.СalendarOfMenu.CalendarViewModel

class ListOfPlacesAdapter (private val places: List<Int>, private val context: Context, private val lpi:list_of_places_interface) : RecyclerView
.Adapter<ListOfPlacesAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

           val placeNumber = itemView.findViewById<TextView>(R.id.place_number)!!
           val layoutNumber = itemView.findViewById<LinearLayout>(R.id.layoutOfPlaceNumber)!!

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.i("Adapter", "OnBind")
        holder.placeNumber.text = "Место № ${places[position]+1}"
        holder.layoutNumber.setOnClickListener {
            lpi.setCheckMenuFragment(places[position])
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.i("Adapter", "OnCreateViewHolder")
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_of_place, parent, false)
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        Log.i("Adapter", "getItemCount ${places.size}")
        return places.size
    }


}