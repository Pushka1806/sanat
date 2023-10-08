package com.example.sanatoriy.InternetConnection.model.GetUserMenu

import com.example.sanatoriy.InternetConnection.model.SendUserMenu.TypeOfFoodIntakeItems
import com.example.sanatoriy.InternetConnection.model.commonMenu.CommonTypeOfFoodIntakeItems
import com.google.gson.annotations.SerializedName


data class UserMenu (

  @SerializedName("typeOfFoodIntakeItems" ) var typeOfFoodIntakeItems : ArrayList<CommonTypeOfFoodIntakeItems> = arrayListOf(),
  @SerializedName("targetDate"            ) var targetDate            : String?                          = null

)