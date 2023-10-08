package com.example.sanatoriy.InternetConnection.model.commonMenu


import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("menu" ) var menu : CommonMenuItemModel = CommonMenuItemModel()

)