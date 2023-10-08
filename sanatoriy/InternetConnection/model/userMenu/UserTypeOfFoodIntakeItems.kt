package com.example.sanatoriy.InternetConnection.model.userMenu

import com.example.sanatoriy.InternetConnection.model.commonMenu.TypeOfDishItem
import com.google.gson.annotations.SerializedName


data class UserTypeOfFoodIntakeItems(
  @SerializedName("typeOfFoodIntakeItemId" ) var typeOfFoodIntakeItemId: String?         = null,
  @SerializedName("typeOfDishItems"        ) var typeOfDishItems: MutableList<UserTypeOfDishItem> = mutableListOf()
)
