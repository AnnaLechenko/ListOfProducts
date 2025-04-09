package com.annalech.listofproducts.di

import android.app.Application
import com.annalech.listofproducts.data.AppDataBase
import com.annalech.listofproducts.data.ShopListDao
import com.annalech.listofproducts.data.ShopListRepositoryImpl
import com.annalech.listofproducts.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindRepository(impl: ShopListRepositoryImpl) : ShopListRepository

    companion object{
        @Provides
        @ApplicationScope
        fun providesDao(application: Application):ShopListDao{
            return AppDataBase.getInstance(application).shopListDao()
        }
    }

}