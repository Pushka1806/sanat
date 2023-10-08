package com.example.sanatoriy.data.user

import com.example.sanatoriy.InternetConnection.model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.model.CommonMenuModel
import com.example.sanatoriy.InternetConnection.model.GetUserMenu.GetUserMenuModel
import com.example.sanatoriy.InternetConnection.model.GetUserMenu.UserData
import com.example.sanatoriy.InternetConnection.model.Table.userTable
import com.example.sanatoriy.InternetConnection.model.UserListModel
import com.example.sanatoriy.UserMenuModel
import javax.inject.Singleton

@Singleton
class MenuSetting {
    var listTypeofFood : AnswerListofCatalogs? = null
    var listTypeOfDish: AnswerListofCatalogs? = null
    var globalMenu: CommonMenuModel? = null
    var userMenuFromResponse:List<UserData>? =null
    var userTable : userTable = userTable(10)//ЗАЧЕМММММ
    var userMenu : UserListModel = UserListModel(11) /////ЗАЧЕМММММММ
    var checkMenuDate: String = "000000"
    var zakazMenuDate:String = "00000"
    var listOfPlaces = mutableListOf<Int>(0,1,2,3,4,5)
}