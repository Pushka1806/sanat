package com.example.sanatoriy.InternetConnection.model.SendUserMenu

import com.google.gson.annotations.SerializedName


data class TypeOfFoodIntakeItems(

  @SerializedName("typeOfFoodIntakeItemId" ) var typeOfFoodIntakeItemId: String?           = null,
  @SerializedName("dishes"                 ) var dishes: MutableList<String?> = mutableListOf()

)