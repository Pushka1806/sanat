package com.example.sanatoriy.bussiness.СalendarOfMenu

import android.util.Log
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.model.Dishes
import com.example.sanatoriy.InternetConnection.model.ErrorMessageModel
import com.example.sanatoriy.InternetConnection.model.GetUserMenu.GetUserMenuModel
import com.example.sanatoriy.InternetConnection.model.GetUserMenu.UserData
import com.example.sanatoriy.data.user.UserDataRepositoriy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CalendarViewModel @Inject constructor(private val UDR: UserDataRepositoriy) {

    fun getUserId(): String {
        return UDR.userId
    }

    fun setZakazMenuDate(date: String) {
        UDR.setZakazMenuDAte(date)
    }

    fun setCheckMenuDate(date: String) {
        UDR.setCheckMenuDate(date)
    }

    fun getListOfPlaceNumbers():List<Int>{
        return UDR.getListOfPlaceNumbers()
    }
    fun setUserMenuFromResponse(data: List<UserData>){
        UDR.setUserMenuFromResponse(data)}

    fun getDishesForCheckUserMenu(placeNumber:Int):HashMap<String,List<Dishes>>{
        return UDR.getHashMapDishesOfPlaceNumber(placeNumber)
    }


    fun responseGetUserMenu() {
        Log.i("GetUserMenu", "Дата для просмотра пользовательского меню ${UDR.getCheckMenuDate()}")
        Common.retrofitService.getUsersMenu(getUserId(), UDR.getCheckMenuDate()).enqueue(object :
            Callback<GetUserMenuModel> {
            override fun onResponse(
                call: Call<GetUserMenuModel>,
                response: Response<GetUserMenuModel>
            ) {
                if (response.code() == 200) { // блокировка уже выбранных пользователей
                    setUserMenuFromResponse(response.body()!!.data)
                    response.body()!!.data.forEach() {
                        // disableListofUsers.add(it.placeNumber.toString().toInt())

                    }

                } else if (response.code() == 500) {
                    Log.i("Response", "GETUSERMENU ERROR")
                    val gson = GsonBuilder().create()
                    var pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())

                }
            }

            override fun onFailure(call: Call<GetUserMenuModel>, t: Throwable) {
                Log.i("Server", "GETUSERMENU SERVER ERROR")
            }

        })
    }
    fun setTypeOfFoodCatalog() {
        Common.retrofitService.getCatalogTypeOfFoodIntake().enqueue(object :
            Callback<AnswerListofCatalogs> {
            override fun onResponse(
                call: Call<AnswerListofCatalogs>,
                response: Response<AnswerListofCatalogs>
            ) {
                if (response.code() == 200) {
//                    UDR.typeOfFood = response.body()!!
                    UDR.setTypeOfFood(response.body()!!)
                    responseGetUserMenu()
                    Log.i("CATALOG", UDR.typeOfFood.toString())

                } else if (response.code() == 401) {
                    Log.i("Response", "GetCatalogTypeOFoodIntake ERROR")
                    val gson = GsonBuilder().create()
                    var pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())
                }
            }

            override fun onFailure(call: Call<AnswerListofCatalogs>, t: Throwable) {

            }

        })
    }


}