package com.example.sanatoriy.bussiness.tableOfUsers.editMenu

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.MyApplication
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.menuList.CustomRecyclerAdapter
import com.example.sanatoriy.bussiness.menuList.MenuViewModel
import com.example.sanatoriy.bussiness.tableOfUsers.TableOfUsersActivity
import javax.inject.Inject

class EditMenuActivity : AppCompatActivity() {
    @Inject
    lateinit var editMenuModel: EditMenuViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var arguments = intent.extras
        var usersComponent = (application as MyApplication).appComponent.tableComponent().create()
        usersComponent.inject(this)
        setContentView(R.layout.activity_edit_menu)
        editMenuModel.typeOfFood = arguments!!.getString("typeOfFood")
        editMenuModel.typeOfDish = arguments.getString("typeOfDish")
        editMenuModel.placeNumber = arguments.getInt("placeNumber")
        editMenuModel.disableListofUsers.add(editMenuModel.placeNumber!!)
        editMenuModel.nameTypeOfFood =
            editMenuModel.findValueFromCatalog(editMenuModel.udr.typeOfFood!!,
                editMenuModel.typeOfFood!!
            )!!
        editMenuModel.nameTypeOfDish =
            editMenuModel.findValueFromCatalog(editMenuModel.udr.typeOfDish!!,
                editMenuModel.typeOfDish!!
            )!!
        var recycler = findViewById<RecyclerView>(R.id.recyclerViewElement)
        DataAdapterOfCommonMenu(recycler)
    }

    fun DataAdapterOfCommonMenu(recyclerAdapter: RecyclerView){
        val adapter = EditUserItemAdapter(editMenuModel.getDishes(),editMenuModel)
        recyclerAdapter.adapter = adapter
    }
}