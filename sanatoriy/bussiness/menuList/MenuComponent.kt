package com.example.sanatoriy.bussiness.menuList

import com.example.sanatoriy.data.user.LoggedUserScope
import dagger.Subcomponent

// Scope annotation that the UserComponent uses
// Classes annotated with @LoggedUserScope will have a unique instance in this Component
// Definition of a Dagger subcomponent
@LoggedUserScope
@Subcomponent
interface MenuComponent {
    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MenuComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MenuListActivity)

}