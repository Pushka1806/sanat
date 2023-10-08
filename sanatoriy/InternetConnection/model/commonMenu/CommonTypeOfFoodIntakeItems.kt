package com.example.sanatoriy.InternetConnection.model.commonMenu

import com.google.gson.annotations.SerializedName


data class CommonTypeOfFoodIntakeItems (

  @SerializedName("typeOfFoodIntakeId" ) var typeOfFoodIntakeItemId : String?         = null,
  @SerializedName("typeOfDishItems"         ) var typeOfDishItems         : List<TypeOfDishItem> = listOf()

)