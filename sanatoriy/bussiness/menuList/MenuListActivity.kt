package com.example.sanatoriy.bussiness.menuList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.model.CommonMenuModel
import com.example.sanatoriy.InternetConnection.model.GetUserMenu.GetUserMenuModel
import com.example.sanatoriy.InternetConnection.model.registration.RegisterModel
import com.example.sanatoriy.MyApplication
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.tableOfUsers.TableOfUsersActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import javax.inject.Inject

class MenuListActivity : AppCompatActivity() {

    lateinit var textTypeOfFood: TextView
    lateinit var textTypeOfDish: TextView
    lateinit var nextButton: Button
    lateinit var recycler: RecyclerView
    @Inject
    lateinit var menuViewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val menuComponent = (application as MyApplication).appComponent.menuComponent().create()
        menuComponent.inject(this)
        setContentView(R.layout.menu_list)

        SetupViews()
        menuViewModel.initCatalogs() // инициализация справочников с сервера

        menuViewModel.catalogData.observe(this, Observer { // если все каталоги заполнены, вызываем глобальное меню
            if(it == "success"){
                Log.i("TAG", "GetCatalogData")
//                Log.i("TAG", menuViewModel.listTypeofFood.toString())
//                Log.i("TAG", menuViewModel.listTypeOfDish.toString())
                menuViewModel.ResponceGetMenu()
            }
        })
        menuViewModel.page.observe(this, Observer<Int> {
            Log.i("TAG","observe ${it}")
            if(it == 999){
                startActivity(Intent(this, TableOfUsersActivity::class.java))
            }
            else if(it == 404){
                Log.i("TAG", "Ошибка меню")
            }
            else if(it > 0){
                InitTextViews()
                DataAdapterOfCommonMenu(recycler)
            }

        })

    }

    fun DataAdapterOfCommonMenu(recyclerAdapter: RecyclerView){
//        val bludes = menuViewModel._menu!!.data.menu.typeOfFoodIntakeItems[menuViewModel.idTypeOfFood].typeOfDishItems[menuViewModel.idTypeOfDish].dishes
//        Log.i("TAG", "typeOfFoodSize ${menuViewModel._menu!!.data.menu.typeOfFoodIntakeItems[0].typeOfDishItems[0].dishes.size}")
//        Log.i("TAG", "idTypeOfFood ${menuViewModel.idTypeOfFood} idTypeOfDish ${menuViewModel.idTypeOfDish }" +bludes.toString())
        val adapter = CustomRecyclerAdapter(menuViewModel.getDishes(), applicationContext, menuViewModel)
        recyclerAdapter.adapter = adapter
    }

    fun SetupViews(){
        textTypeOfFood = findViewById(R.id.typeOfFood)
        textTypeOfDish = findViewById(R.id.typeOfDish)

        recycler =  findViewById(R.id.recyclerViewElement)
        recycler.layoutManager = LinearLayoutManager(this)
    }
    fun InitTextViews(){
        textTypeOfFood.text = menuViewModel.nameTypeOfFood
        textTypeOfDish.text = menuViewModel.nameTypeOfDish
    }
    companion object{
        private const val ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    }

}

