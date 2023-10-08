package com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.menuList.CustomRecyclerAdapter
import com.example.sanatoriy.bussiness.СalendarOfMenu.CalendarViewModel
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [ListOfPlacesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListOfPlacesFragment : Fragment() {
    // TODO: Rename and change types of parameters

    @Inject
    lateinit var calendarViewModel: CalendarViewModel
    lateinit var lpi: list_of_places_interface
    lateinit var aplcontext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onAttach(context: Context) {
        lpi = (context) as list_of_places_interface
        aplcontext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_places, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarViewModel = lpi.getViewModel()
        calendarViewModel.setTypeOfFoodCatalog()
        Log.i("List",calendarViewModel.getListOfPlaceNumbers().toString())
        val adapter = ListOfPlacesAdapter(calendarViewModel.getListOfPlaceNumbers(), context = aplcontext, lpi )
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerViewElement)
        recycler.layoutManager = LinearLayoutManager(aplcontext);
        recycler.adapter = adapter

    }
}