package com.example.sanatoriy.InternetConnection.model


import com.example.sanatoriy.InternetConnection.model.commonMenu.Data
import com.google.gson.annotations.SerializedName


data class CommonMenuModel(

  @SerializedName("status") var status: Int?= null,
  @SerializedName("data") var data: Data = Data(),
  @SerializedName("paging") var paging: ArrayList<String>? = arrayListOf()

)