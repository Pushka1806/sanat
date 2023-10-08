package com.example.sanatoriy.bussiness.tableOfUsers

import com.example.sanatoriy.InternetConnection.model.SendUserMenu.SendUserMenuModel

interface set_fragment_interface {
    fun setUserFragment(user: Int)
    fun backFragment()
    fun convertMenuForSend(): List<SendUserMenuModel>
    fun getUserId(): String
    fun backToCalendar()

}