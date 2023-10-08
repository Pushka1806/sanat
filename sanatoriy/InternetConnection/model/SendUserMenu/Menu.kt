package com.example.sanatoriy.InternetConnection.model.SendUserMenu

import com.google.gson.annotations.SerializedName


data class Menu (

  @SerializedName("typeOfFoodIntakeItems" ) var typeOfFoodIntakeItems : List<TypeOfFoodIntakeItems> = mutableListOf()

)