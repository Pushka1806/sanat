package com.example.sanatoriy.InternetConnection.model.Catalogs

import com.google.gson.annotations.SerializedName


data class CatalogData (

  @SerializedName("id"          ) var id          : String? = null,
  @SerializedName("code"        ) var code        : Int?    = null,
  @SerializedName("description" ) var description : String? = null,
  @SerializedName("createdAt"   ) var createdAt   : String? = null

)