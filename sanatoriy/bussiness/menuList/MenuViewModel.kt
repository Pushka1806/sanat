package com.example.sanatoriy.bussiness.menuList

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.model.Catalogs.CatalogData
import com.example.sanatoriy.InternetConnection.model.Dishes
import com.example.sanatoriy.InternetConnection.model.CommonMenuModel
import com.example.sanatoriy.InternetConnection.model.ErrorMessageModel
import com.example.sanatoriy.InternetConnection.model.GetUserMenu.GetUserMenuModel
import com.example.sanatoriy.InternetConnection.model.userMenu.UserTypeOfDishItem
import com.example.sanatoriy.InternetConnection.model.userMenu.UserTypeOfFoodIntakeItems
import com.example.sanatoriy.data.user.UserDataRepositoriy
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val UDR: UserDataRepositoriy) {


    var idTypeOfFood = -2
    var idTypeOfDish = -2
    var nameTypeOfFood = "None"
    var nameTypeOfDish = "None"
    var disableListofUsers = mutableListOf<Int>()
    private var _page = MutableLiveData<Int>(0)
    val page: LiveData<Int>
        get() {
            return _page
        }

    private var _catalogData = MutableLiveData<String>()
    val catalogData: LiveData<String>
        get() {
            return _catalogData
        }

    fun nextPage() {
        if (idTypeOfFood == -1) {
            try {
                idTypeOfFood = 0
                idTypeOfDish = 0
                initTextFront()
                _page.value = _page.value?.plus(1)
            } catch (e: java.lang.Exception) {
                _page.value = 404   // обозначает что в меню ошибка
            }
        } else if (idTypeOfDish + 1 in 0 until UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfDishItems.size) {
            idTypeOfDish += 1
            initTextFront()
            _page.value = _page.value?.plus(1)
        } else if (idTypeOfFood + 1 in 0 until UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems.size) {
            idTypeOfFood += 1
            idTypeOfDish = 0
            initTextFront()
            _page.value = _page.value?.plus(1)
        } else {
            Log.i("TAG", "last page1")
            _page.value = 999 // остановка выбора и перевод на вывод меню на каждого человека
        }
    }

    fun initTextFront() {

        nameTypeOfFood =
            findValueFromCatalog(
                UDR.typeOfFood!!,
                UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfFoodIntakeItemId.toString()
            )
                .toString()
        nameTypeOfDish =
            findValueFromCatalog(
                UDR.typeOfDish!!,
                UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfDishItems[idTypeOfDish].typeOfDishItemId.toString()
            )
                .toString()
    }

    fun initCatalogs() {
        ResponceGetTypeOfFoodIntake()
        ResponceGetTypeOfDish()
    }

    fun getZakazDate(): String {
        return UDR.getZakazMenuDate()
    }


    fun ResponceGetMenu() {
        Common.retrofitService.getMenu(getZakazDate())?.enqueue(object : Callback<CommonMenuModel> {
            override fun onResponse(
                call: Call<CommonMenuModel>,
                response: Response<CommonMenuModel>
            ) {
                if (response.code() == 200) {
                    Log.i("Response", "GETMENU OK")
                    UDR.setGlobalMenu(response.body())
                    ResponseGetUserMenu()

                } else if (response.code() == 401) {
                    Log.i("Response", "GETMENU ERROR")
                    val gson = GsonBuilder().create()
                    var pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())
                }
            }

            override fun onFailure(call: Call<CommonMenuModel>, t: Throwable) {
                Log.i("TAG", " Server Error GetMenu")
            }

        })
    }

    fun ResponseGetUserMenu() {
        val tomorrow = LocalDate.now()
            .plusDays(1)//функция возвращает дату а нам нужно на день вперёд       //  val tomorrow = LocalDate.now()
        val dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssX");
        val formattedTomorrow = tomorrow.atStartOfDay().atOffset(ZoneOffset.UTC).format(dtf);
        Log.i("TIME", formattedTomorrow)
        Common.retrofitService.getUsersMenu(UDR.userId, formattedTomorrow).enqueue(object :
            Callback<GetUserMenuModel> {
            override fun onResponse(
                call: Call<GetUserMenuModel>,
                response: Response<GetUserMenuModel>
            ) {
                if (response.code() == 200) { // блокировка уже выбранных пользователей
                    //placeNumber возврает места от 1, поэтому отнимаем 1 чтобы массив начинался с 0
                    response.body()!!.data.forEach() {
                        disableListofUsers.add(it.placeNumber.toString().toInt()-1)
                    }
                    startPrintMenu()
                    nextPage()
                    //Log.i("TAG",disableListofUsers.toString())
                } else if (response.code() == 500) {
                    Log.i("Response", "GETUSERMENU ERROR")
                    val gson = GsonBuilder().create()
                    var pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())
                    startPrintMenu()
                    nextPage()
                }
            }

            override fun onFailure(call: Call<GetUserMenuModel>, t: Throwable) {
                Log.i("Server", "GETUSERMENU SERVER ERROR")
            }

        })
    }

    fun ResponceGetTypeOfDish() {
        Common.retrofitService.getCatalogTypeOfDish().enqueue(object :
            Callback<AnswerListofCatalogs> {
            override fun onResponse(
                call: Call<AnswerListofCatalogs>,
                response: Response<AnswerListofCatalogs>
            ) {
                if (response.code() == 200) {
                    UDR.setTypeOfDish(response.body()!!)
                    if (UDR.typeOfFood != null) {
                        _catalogData.value = "success"
                    } else {
                        Log.i("TAG", "Catalog null")
                    }
                } else if (response.code() == 401) {
                    Log.i("Response", "GetCatalogTypeOfDish ERROR")
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

    fun ResponceGetTypeOfFoodIntake() {
        Log.i("CATALOG", UDR.typeOfFood.toString())
        if (UDR.typeOfDish != null) {
            _catalogData.value = "success"
        } else {
            Log.i("TAG", "Catalog null")
        }
        Common.retrofitService.getCatalogTypeOfFoodIntake().enqueue(object :
            Callback<AnswerListofCatalogs> {
            override fun onResponse(
                call: Call<AnswerListofCatalogs>,
                response: Response<AnswerListofCatalogs>
            ) {
                if (response.code() == 200) {
//                    UDR.typeOfFood = response.body()!!
                    UDR.setTypeOfFood(response.body()!!)
                    Log.i("CATALOG", UDR.typeOfFood.toString())
                    if (UDR.typeOfDish != null) {
                        _catalogData.value = "success"
                    } else {
                        Log.i("TAG", "Catalog null")
                    }
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

    fun findValueFromCatalog(catalog: AnswerListofCatalogs, value: String): String? {
        return UDR.findValueFromCatalog(catalog,value)
    }

    fun getDishes(): List<Dishes> {
        return UDR.getDishes(idTypeOfFood, idTypeOfDish)
    }


    fun startPrintMenu() {
        idTypeOfFood = -1
        idTypeOfDish = -1
    }

    @SuppressLint("SuspiciousIndentation")
    fun findDish(DishId: String, placeNumber: Int): Boolean {
        var dishesList: List<Dishes>? = UDR.getDishesOfPlaceNumber(placeNumber)
        if (dishesList == null) {
            return false
        } else {
            dishesList.forEach { k ->
                if (k.id == DishId) {
                    return true
                }
            }
        }
        return false
    }

    fun addDish(bludo: Dishes, placeNumber: Int) {
        Log.i("filter_item", "addBludo")
        UDR.userTable.itemOfTable[placeNumber].listOfSelectedDishes!!.add(bludo)
    }

    fun removeDish(bludo: Dishes, placeNumber: Int) {
        Log.i("filter_item", "removeBludo")
        UDR.userTable.itemOfTable[placeNumber].listOfSelectedDishes!!.remove(bludo)
    }
}



