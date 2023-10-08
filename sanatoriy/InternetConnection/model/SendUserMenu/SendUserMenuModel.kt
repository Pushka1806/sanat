package com.example.sanatoriy.InternetConnection.model.SendUserMenu

import com.example.sanatoriy.InternetConnection.model.commonMenu.TypeOfDishItem
import com.google.gson.annotations.SerializedName


data class SendUserMenuModel (

  @SerializedName("menu"        ) var menu        : Menu? = Menu(),
  @SerializedName("placeNumber" ) var placeNumber : Int?  = null

)