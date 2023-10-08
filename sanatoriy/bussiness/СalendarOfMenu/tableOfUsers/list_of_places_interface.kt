package com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers

import com.example.sanatoriy.bussiness.СalendarOfMenu.CalendarViewModel

interface list_of_places_interface {
    fun getViewModel():CalendarViewModel
    fun setCheckMenuFragment(placeNumber:Int)
}