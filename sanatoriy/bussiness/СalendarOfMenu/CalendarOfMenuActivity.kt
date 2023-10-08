package com.example.sanatoriy.bussiness.СalendarOfMenu

import android.R.attr.key
import android.R.attr.value
import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sanatoriy.MyApplication
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.menuList.MenuListActivity
import com.example.sanatoriy.bussiness.СalendarOfMenu.calendar.CalendarFragment
import com.example.sanatoriy.bussiness.СalendarOfMenu.calendar.calendar_interface
import com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers.ListOfPlacesFragment
import com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers.checkMenu.CheckMenuFragment
import com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers.checkMenu.check_menu_interface
import com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers.list_of_places_interface
import javax.inject.Inject


class CalendarOfMenuActivity : AppCompatActivity(), calendar_interface, list_of_places_interface, check_menu_interface {
    @Inject
    lateinit var calendarViewModel: CalendarViewModel

    @SuppressLint("MissingInflatedId", "SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_of_menu)
        
        var calendarComponent = (application as MyApplication).appComponent.calendarComponent().create()
        calendarComponent.inject(this)
        supportFragmentManager.beginTransaction()
            .add(R.id.calendar_fragment_holder, CalendarFragment())
            .commit()


    }

    override fun setCreateMenuActivity() {
        startActivity(Intent(this, MenuListActivity::class.java))
        finish()
    }

    override fun setCheckMenuFragment(placeNumber:Int) {
        val fragment = CheckMenuFragment()
        val bundle = Bundle()
        bundle.putInt("place_number", placeNumber)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.calendar_fragment_holder, fragment)
            .addToBackStack(CheckMenuFragment::class.java.simpleName)
            .commit()
    }

    override fun setListOfPlacesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.calendar_fragment_holder, ListOfPlacesFragment())
            .addToBackStack(ListOfPlacesFragment::class.java.simpleName)
            .commit()
    }

    override fun setCheckMenuDate(date: String) {
       calendarViewModel.setCheckMenuDate(date)
    }

    override fun setZakazMenuDate(date: String) {
        calendarViewModel.setZakazMenuDate(date)
    }

    override fun getViewModel(): CalendarViewModel {
        return calendarViewModel
    }

    override fun getViewModelListMenu(): CalendarViewModel {
        return  calendarViewModel
    }


}