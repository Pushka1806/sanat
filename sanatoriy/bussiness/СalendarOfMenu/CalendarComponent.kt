package com.example.sanatoriy.bussiness.СalendarOfMenu

import com.example.sanatoriy.bussiness.СalendarOfMenu.calendar.CalendarFragment
import com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers.ListOfPlacesFragment
import com.example.sanatoriy.bussiness.СalendarOfMenu.tableOfUsers.checkMenu.CheckMenuFragment
import com.example.sanatoriy.data.user.LoggedUserScope
import dagger.Subcomponent

@LoggedUserScope
@Subcomponent
interface CalendarComponent {
    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): CalendarComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: CalendarOfMenuActivity)
    fun inject(fragment: CalendarFragment)
    fun inject(fragment: CheckMenuFragment)
    fun inject(fragment: ListOfPlacesFragment)


}