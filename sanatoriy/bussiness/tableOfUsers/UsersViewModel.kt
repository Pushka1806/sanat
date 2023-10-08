package com.example.sanatoriy.bussiness.tableOfUsers

import com.example.sanatoriy.InternetConnection.model.SendUserMenu.SendUserMenuModel
import com.example.sanatoriy.data.user.UserDataRepositoriy
import javax.inject.Inject

class UsersViewModel @Inject constructor(private val UDR: UserDataRepositoriy ) {

//    fun getUserDishes(place_number:Int):List<Dishes>{
//        return UDR.getUserDishes(place_number)
//    }
//
    fun getMenuForSend(): List<SendUserMenuModel> {
        return UDR.getMenuForSend()
    }

    fun getUserId():String{
        return UDR.userId

    }




}