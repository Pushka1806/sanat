package com.example.sanatoriy.InternetConnection.model.userMenu

import com.example.sanatoriy.InternetConnection.model.Dishes
import com.google.gson.annotations.SerializedName


data class UserTypeOfDishItem (
  @SerializedName("typeOfDishItemId" ) var typeOfDishItemId : String?           = null,
  @SerializedName("dishes"           ) var bludo           : Dishes? = null
)