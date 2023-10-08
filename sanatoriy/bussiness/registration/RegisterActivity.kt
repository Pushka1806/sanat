package com.example.sanatoriy.bussiness.registration

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.model.ErrorMessageModel
import com.example.sanatoriy.InternetConnection.model.registration.RegisterModel
import com.example.sanatoriy.InternetConnection.model.tableNumberModel
import com.example.sanatoriy.MyApplication
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.menuList.MenuListActivity
import com.example.sanatoriy.bussiness.СalendarOfMenu.CalendarOfMenuActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
private const val SUCCESS_VALUE = "success"

class RegisterActivity  : AppCompatActivity() {

    lateinit var  numberTableView: EditText
    @Inject
    lateinit var registerViewModel: RegisterViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userManager = (application as MyApplication).appComponent.userManager()
        if (!userManager.isUserLoggedIn()) {
            Log.i("TAG", "isUserLoggedIn")
            userManager.userJustLoggedIn()
            if (userManager.isUserRegistered()) {
                startActivity(Intent(this, CalendarOfMenuActivity::class.java))
                finish()
            }

        }
        userManager.userComponent!!.inject(this)

        setContentView(R.layout.register)
        setupViews()
        registerViewModel.tableState.observe(this, Observer<String> { state ->
            when (state) {
                SUCCESS_VALUE -> {
                    startActivity(
                        Intent(this, CalendarOfMenuActivity::class.java))
                    finish()
                }

            }
        })

        findViewById<Button>(R.id.bt_main).setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                registerViewModel.registerUser(numberTableView.text.toString().toInt())
            }
        })
    }

            @SuppressLint("SetTextI18n")
            fun setupViews() {
                var bt = findViewById<TextView>(R.id.hello)
                bt.text = bt.text.toString() + registerViewModel.welcomeText
                numberTableView = findViewById<EditText>(R.id.number_table)

            }


}