package com.example.sanatoriy.bussiness.tableOfUsers





import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.model.SendUserMenu.SendUserMenuModel
import com.example.sanatoriy.InternetConnection.model.registration.RegisterModel
import com.example.sanatoriy.MyApplication
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.userDetail.UserMenuDetailFragment
import com.example.sanatoriy.bussiness.СalendarOfMenu.CalendarOfMenuActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TableOfUsersActivity : AppCompatActivity() , set_fragment_interface {

    @Inject
    lateinit var usersViewModel: UsersViewModel
    lateinit var usersComponent: TableComponent

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersComponent = (application as MyApplication).appComponent.tableComponent().create()
        usersComponent.inject(this)
        setContentView(R.layout.activity_table_of_users)

        supportFragmentManager.beginTransaction()
            .add(R.id.table_fragment_holder, TableUsersFragment())
            .commit()

    }

    override fun setUserFragment(user: Int) {
        var detail_fragment= UserMenuDetailFragment()
        val b = Bundle()
        b.putInt("table_place_number", user)
        detail_fragment.arguments = b
        supportFragmentManager.beginTransaction()
            .replace(R.id.table_fragment_holder, detail_fragment)
            .addToBackStack(UserMenuDetailFragment::class.java.simpleName)
            .commit()
    }

    override fun backFragment() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }
    }
    override fun convertMenuForSend(): List<SendUserMenuModel> {
        return usersViewModel.getMenuForSend()
    }

    override fun getUserId(): String {
        return usersViewModel.getUserId()
    }

    override fun backToCalendar() {
        startActivity(Intent( this, CalendarOfMenuActivity::class.java))
        finish()
    }


//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount > 0) {
//            supportFragmentManager.popBackStack()
//        } else {
//            super.onBackPressed()
//        }
//    }
}