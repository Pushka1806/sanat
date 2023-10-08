package com.example.myroutekotlin.InternetConnection.Retrofit

import com.example.sanatoriy.InternetConnection.model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.model.CommonMenuModel
import com.example.sanatoriy.InternetConnection.model.GetUserMenu.GetUserMenuModel
import com.example.sanatoriy.InternetConnection.model.SendUserMenu.SendUserMenuModel
import com.example.sanatoriy.InternetConnection.model.registration.RegisterModel
import com.example.sanatoriy.InternetConnection.model.tableNumberModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

//
interface RetrofitService {

    @GET("menu/getGlobalMenuByDate")
    fun getMenu(@Query("targetDate") targetDate:String): Call<CommonMenuModel>?

    @POST("user/register")
    fun registerUserTable(@Body table: tableNumberModel) : Call<RegisterModel>

    @POST("menu/createMenu")
    fun createUserMenu(@Header("UserId") userId : String, @Body menu: List<SendUserMenuModel>): Call<RegisterModel>

    @GET("menu/getUserMenuByDate")
    fun getUsersMenu(@Header("UserId") userId : String, @Query("targetDate") targetDate: String): Call<GetUserMenuModel>

    @GET("dish/all/typeOfDish")
    fun getCatalogTypeOfDish() : Call<AnswerListofCatalogs>

    @GET("dish/all/typeOfFoodIntake")
    fun getCatalogTypeOfFoodIntake() : Call<AnswerListofCatalogs>

}

