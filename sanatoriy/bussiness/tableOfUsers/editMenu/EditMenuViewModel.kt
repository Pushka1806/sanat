package com.example.sanatoriy.bussiness.tableOfUsers.editMenu

import android.util.Log
import com.example.sanatoriy.InternetConnection.model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.model.Dishes
import com.example.sanatoriy.InternetConnection.model.userMenu.UserTypeOfDishItem
import com.example.sanatoriy.InternetConnection.model.userMenu.UserTypeOfFoodIntakeItems
import com.example.sanatoriy.data.user.UserDataRepositoriy
import javax.inject.Inject

class EditMenuViewModel  @Inject constructor(private val UDR: UserDataRepositoriy) {
    var placeNumber: Int? = null
    var typeOfFood: String? = null
    var typeOfDish:String? = null
    var nameTypeOfFood = "None"
    var nameTypeOfDish = "None"
    var disableListofUsers = mutableListOf<Int>()
    val udr : UserDataRepositoriy
        get() = UDR

    fun findValueFromCatalog(catalog: AnswerListofCatalogs, value : String): String? {
        catalog.data.forEach {
            Log.i("ID", it.id.toString() + " - " + value)
            if(it.id == value){
                return it.description
            }
        }
        return null
    }

    fun getDishes(): List<Dishes>{
        return UDR.getDishes(
            UDR.getNumberFromCatalog(UDR.typeOfFood, typeOfFood!!)!!,
            UDR.getNumberFromCatalog(UDR.typeOfDish, typeOfDish!!)!!
        )
    }

    fun addBludo(bludo: Dishes, place_number: Int) {
        val listTypeOfFood = UDR.userMenu.userList[place_number].menu!!.typeOfFoodIntakeItems
        if (listTypeOfFood.isEmpty()) {
            listTypeOfFood.add(
                UserTypeOfFoodIntakeItems(
                    bludo.typeOfFoodIntakeItemId,
                    mutableListOf(UserTypeOfDishItem(bludo.typeOfDishId, bludo))
                )
            )
            Log.i("TAG", "Add Bludo ${bludo.name}")
        } else {
            checkTypeOfFood(listTypeOfFood, bludo)
        }
    }

    fun checkTypeOfFood(list: MutableList<UserTypeOfFoodIntakeItems>, bludo: Dishes) {
        var find_value = false
        for (i in list.indices) {
            if (list[i].typeOfFoodIntakeItemId == bludo.typeOfFoodIntakeItemId) {
                checkTypeOfDish(list[i].typeOfDishItems, bludo)
                find_value = true
                break
            }
        }
        if (!find_value) {
            addTypeOfFood(list, bludo)
        }
    }

    fun checkTypeOfDish(list: MutableList<UserTypeOfDishItem>, _bludo: Dishes) {
        var find_value = false
        for (i in list.indices) {
            if (list[i].typeOfDishItemId == _bludo.typeOfDishId) { //сделать если пууто если тоже самое
                list[i].bludo = _bludo//если эта подкатегория существует меняем блюдо
                Log.i("TAG", "Replace Bludo ${_bludo.name}")
                find_value = true
                break
            }
        }
        if (!find_value) {
            addTypeOfDish(list, _bludo) //добавляем подкатегорию и блюдо
        }
    }

    fun addTypeOfDish(
        list: MutableList<UserTypeOfDishItem>,
        bludo: Dishes
    ): MutableList<UserTypeOfDishItem> {
        list.add(UserTypeOfDishItem(bludo.typeOfDishId, bludo))
        Log.i("TAG", "Add Bludo ${bludo.name}")
        return list
    }

    fun addTypeOfFood(list: MutableList<UserTypeOfFoodIntakeItems>, bludo: Dishes) {
        list.add(
            UserTypeOfFoodIntakeItems(
                bludo.typeOfFoodIntakeItemId,
                addTypeOfDish(mutableListOf(), bludo)
            )
        )
    }



}