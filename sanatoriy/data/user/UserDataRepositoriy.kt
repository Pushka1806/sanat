package com.example.sanatoriy.data.user

import com.example.sanatoriy.InternetConnection.model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.model.CommonMenuModel
import com.example.sanatoriy.InternetConnection.model.Dishes
import com.example.sanatoriy.InternetConnection.model.GetUserMenu.GetUserMenuModel
import com.example.sanatoriy.InternetConnection.model.GetUserMenu.UserData
import com.example.sanatoriy.InternetConnection.model.SendUserMenu.Menu
import com.example.sanatoriy.InternetConnection.model.SendUserMenu.SendUserMenuModel
import com.example.sanatoriy.InternetConnection.model.SendUserMenu.TypeOfFoodIntakeItems
import com.example.sanatoriy.InternetConnection.model.Table.userTable
import com.example.sanatoriy.InternetConnection.model.UserListModel
import javax.inject.Inject

//@LoggedUserScope
class UserDataRepositoriy @Inject constructor(
    private val user: UserManager,
    private val menu: MenuSetting
    ) {

    val userId: String
        get() = userManager.userId

    val userManager: UserManager
        get() = user

    val userTable: userTable
        get() = menu.userTable
    val userMenu: UserListModel
        get() = menu.userMenu

    val globalmenu: CommonMenuModel?
        get() = menu.globalMenu!!

    fun setGlobalMenu(_menu: CommonMenuModel?) {
        menu.globalMenu = _menu
    }
    fun setUserMenuFromResponse(data: List<UserData>){
        menu.userMenuFromResponse = data
    }

    val typeOfFood: AnswerListofCatalogs?
        get() = menu.listTypeofFood

    val typeOfDish: AnswerListofCatalogs?
        get() = menu.listTypeOfDish

    fun setTypeOfFood(catalog: AnswerListofCatalogs) {
        menu.listTypeofFood = catalog
    }
    
    
    fun setTypeOfDish(catalog: AnswerListofCatalogs) {
        menu.listTypeOfDish = catalog
    }

    fun getDishes(idTypeOfFood: Int, idTypeOfDish: Int): List<Dishes> {
        return menu.globalMenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfDishItems[idTypeOfDish].dishes
    }

    fun registerUser(userId: String) {
        user.registerUser(userId)
    }

    fun getDishesOfPlaceNumber(placeNumber: Int): List<Dishes>? {
        return menu.userTable.itemOfTable[placeNumber].listOfSelectedDishes
    }

    fun getNumberFromCatalog(catalog: AnswerListofCatalogs?, value: String): Int? {
        var field = 0
        catalog!!.data.forEach {
            if (it.id == value) {
                return field
            }
        }
        return null
    }

    fun setTableNumber(table_number: Int) {
        menu.userTable = userTable(table_number)
    }

    fun getMenuForSend(): List<SendUserMenuModel> {
        var modelForSend = mutableListOf<SendUserMenuModel>()
        userTable.itemOfTable.forEach { item ->
            var listTypeOfFood = mutableListOf<TypeOfFoodIntakeItems>()
            typeOfFood!!.data.forEach { typef ->
                var listOfDish = mutableListOf<String?>()
                item.listOfSelectedDishes!!.forEach { dish ->
                    if (dish.typeOfFoodIntakeItemId == typef.id) {
                        listOfDish.add(dish.id)
                    }
                }
                if (listOfDish.isNotEmpty()) {
                    listTypeOfFood.add(TypeOfFoodIntakeItems(typef.id, listOfDish))
                }

            }
            if (listTypeOfFood.isNotEmpty()){
                modelForSend.add(
                    SendUserMenuModel(
                        Menu(typeOfFoodIntakeItems = listTypeOfFood),
                        item.placeNumber!!.plus(1)))

            }
        }
        return modelForSend
    }

    fun getCheckMenuDate():String{
        return menu.checkMenuDate
    }
    fun setCheckMenuDate(date:String){
        menu.checkMenuDate = date
    }

    fun getZakazMenuDate():String{
        return menu.zakazMenuDate
    }
    fun setZakazMenuDAte(date:String){
        menu.zakazMenuDate = date
    }

    fun getListOfPlaceNumbers():List<Int>{
        return menu.listOfPlaces
    }

    fun getHashMapDishesOfPlaceNumber(placeNumber: Int):HashMap<String,List<Dishes>>{
        val typeOfFoood = menu.userMenuFromResponse!![placeNumber].menu!!.typeOfFoodIntakeItems
        val dishList = hashMapOf<String,List<Dishes>>()
        typeOfFoood.forEach { typeFood ->
                             val listTypeOfDish = mutableListOf<Dishes>()
                             val title= findValueFromCatalog(menu.listTypeofFood!!,typeFood.typeOfFoodIntakeItemId!!)
                             typeFood.typeOfDishItems.forEach {typeDish->
                                                                listTypeOfDish.addAll(typeDish.dishes) }
                             dishList[title!!] = listTypeOfDish
        }
        return dishList

    }

    fun findValueFromCatalog(catalog: AnswerListofCatalogs, value: String): String? {

        catalog.data.forEach {
            if (it.id == value) {
                return it.description
            }
        }
        return null
    }
}
