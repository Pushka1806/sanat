package com.example.sanatoriy.InternetConnection.model.commonMenu

import com.google.gson.annotations.SerializedName

class CommonMenuItemModel(
    @SerializedName("typeOfFoodIntakeItems" ) var typeOfFoodIntakeItems : List<CommonTypeOfFoodIntakeItems> = listOf()
)
