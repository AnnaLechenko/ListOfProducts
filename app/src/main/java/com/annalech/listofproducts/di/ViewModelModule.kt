package com.annalech.listofproducts.di


import androidx.lifecycle.ViewModel
import com.annalech.listofproducts.presentation.MainViewModel
import com.annalech.listofproducts.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule  {
    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl:MainViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel(impl:ShopItemViewModel):ViewModel
}