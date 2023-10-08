package com.example.sanatoriy.InternetConnection.model.userMenu

import com.google.gson.annotations.SerializedName

class UserMenuItemModel(
    @SerializedName("typeOfFoodIntakeItems" ) var typeOfFoodIntakeItems : MutableList<UserTypeOfFoodIntakeItems> = mutableListOf()
) 