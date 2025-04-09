package com.annalech.listofproducts.presentation

import android.app.Application
import com.annalech.listofproducts.di.DaggerApplicationComponents

class ShopApp:Application() {
    val components by lazy {
DaggerApplicationComponents.factory().create(this)
    }
}