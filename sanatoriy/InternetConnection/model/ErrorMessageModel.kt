package com.example.sanatoriy.InternetConnection.model

import com.google.gson.annotations.SerializedName


data class ErrorMessageModel (

  @SerializedName("status"    ) var status    : Int?    = null,
  @SerializedName("errorText" ) var errorText : String? = null,
  @SerializedName("context"   ) var context   : String? = null

)