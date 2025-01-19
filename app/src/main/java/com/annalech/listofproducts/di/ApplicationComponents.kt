package com.annalech.listofproducts.di

import android.app.Activity
import android.app.Application
import com.annalech.listofproducts.presentation.MainActivity
import com.annalech.listofproducts.presentation.ShopItemActivity
import com.annalech.listofproducts.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Component.Factory

@ApplicationScope
@Component(modules = [
    DataModule::class,
    ViewModelModule::class])
interface ApplicationComponents {

    fun inject(activity: MainActivity)
    fun inject(fragment: ShopItemFragment)


    @Component.Factory
    interface  Factory{
        fun create(
            @BindsInstance application: Application
        ):ApplicationComponents
    }
}