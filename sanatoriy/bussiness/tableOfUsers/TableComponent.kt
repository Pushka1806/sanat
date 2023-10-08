package com.example.sanatoriy.bussiness.tableOfUsers

import com.example.sanatoriy.data.user.LoggedUserScope
import com.example.sanatoriy.bussiness.userDetail.UserMenuDetailFragment
import com.example.sanatoriy.bussiness.tableOfUsers.editMenu.EditMenuActivity
import dagger.Subcomponent

@LoggedUserScope
@Subcomponent
interface TableComponent {
    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): TableComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: TableOfUsersActivity)
    fun inject(fragment: UserMenuDetailFragment)
    fun inject (activity: EditMenuActivity)

}