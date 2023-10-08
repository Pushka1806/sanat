package com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers.checkMenu

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.InternetConnection.model.Dishes
import com.example.sanatoriy.InternetConnection.model.UserListModel
import com.example.sanatoriy.InternetConnection.model.userMenu.UserTypeOfFoodIntakeItems
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers.list_of_places_interface

class CheckMenuAdapter (private val listTypeOfFood : MutableList<UserTypeOfFoodIntakeItems>) : RecyclerView
.Adapter<CheckMenuAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.i("Adapter", "OnBind")


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.i("Adapter", "OnCreateViewHolder")
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_of_place, parent, false)
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        Log.i("Adapter", "getItemCount ${listTypeOfFood.size}")
        return listTypeOfFood.size
    }


}