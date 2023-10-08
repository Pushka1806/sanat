package com.example.sanatoriy.InternetConnection.model.Table

import com.example.sanatoriy.InternetConnection.model.Dishes

class ItemOfTable(var tableNumber: Int) {
    var listOfSelectedDishes :MutableList<Dishes>? = mutableListOf()
    var placeNumber:Int? = tableNumber
}