package com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers.checkMenu


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import com.example.sanatoriy.R

/**
 * A simple [Fragment] subclass.
 * Use the [CheckMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckMenuFragment : Fragment() {
 lateinit var aplContext:Context
 lateinit var cmi:check_menu_interface
 var placeNumber: Int? = null



    ///

    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private var titleList: List<String>? = null
    override fun onAttach(context: Context) {
        aplContext = context
        cmi = context as check_menu_interface
        super.onAttach(context)
    }
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            placeNumber= it.getInt("place_number")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            expandableListView = view.findViewById(R.id.expendableList)
            if (expandableListView != null) {
                val viewModel = cmi.getViewModelListMenu()
                val listData = viewModel.getDishesForCheckUserMenu(placeNumber!!)
                titleList = ArrayList(listData.keys)
                adapter = CustomExpandableListAdapter(aplContext, titleList as ArrayList<String>, listData)
                expandableListView!!.setAdapter(adapter)
//
            }
        }
//        val recycler = view.findViewById<RecyclerView>(R.id.recyclerViewElement)
//        recycler.layoutManager = LinearLayoutManager(aplContext)
//        val adapter = CheckMenuAdapter(cmi.getViewModelListMenu().getUserMenu().userList[0].menu!!.typeOfFoodIntakeItems)
//        recycler.adapter = adapter
    }

