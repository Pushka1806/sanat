package com.example.sanatoriy.bussiness.tableOfUsers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.model.ErrorMessageModel
import com.example.sanatoriy.InternetConnection.model.registration.RegisterModel
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.СalendarOfMenu.CalendarOfMenuActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TableUsersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TableUsersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var fragmentContext : Context
    private lateinit var sfi: set_fragment_interface

    override fun onAttach(context: Context) {
        sfi = context as set_fragment_interface
        fragmentContext = context
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_of_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<androidx.appcompat.widget.LinearLayoutCompat>(R.id.user1_icon).setOnClickListener {
            sfi.setUserFragment(0)
        }
        view.findViewById<androidx.appcompat.widget.LinearLayoutCompat>(R.id.user2_icon).setOnClickListener {
            sfi.setUserFragment(1)
        }

        view.findViewById<androidx.appcompat.widget.LinearLayoutCompat>(R.id.user3_icon).setOnClickListener {
            sfi.setUserFragment(2)
        }

        view.findViewById<androidx.appcompat.widget.LinearLayoutCompat>(R.id.user4_icon).setOnClickListener {
            sfi.setUserFragment(3)
        }
        view. findViewById<Button>(R.id.send_menu_button).setOnClickListener(View.OnClickListener {
            var userMenuForSend = sfi.convertMenuForSend()
            Log.i("TAG",userMenuForSend.toString())
            Common.retrofitService.createUserMenu(sfi.getUserId(),userMenuForSend).enqueue(object:
                Callback<RegisterModel> {
                override fun onResponse(
                    call: Call<RegisterModel>,
                    response: Response<RegisterModel>
                ) {
                    if (response.code() == 500){
                        Log.i("Response", "createUserMenu ERROR")
                        val gson = GsonBuilder().create()
                        var pojo = gson.fromJson(
                            response.errorBody()!!.string(),
                            ErrorMessageModel::class.java)
                        Toast.makeText(fragmentContext,pojo.errorText.toString(),Toast.LENGTH_LONG).show()
                        Log.i("Response" ,pojo.errorText.toString())
                    }
                    else{
                        Log.i("TAG","CreateUserMenu OK")
                    }
                    sfi.backToCalendar()

                }

                override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                    Log.i("TAG","CreateUserMenu  Error")
                }

            })
        })
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TableUsersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TableUsersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}