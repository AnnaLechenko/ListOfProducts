package com.annalech.listofproducts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.annalech.listofproducts.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider


class ViewModelFactory @Inject constructor(
    private val viewModelProvider:
            @JvmSuppressWildcards Map<Class<out ViewModel>,Provider<ViewModel>>
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  viewModelProvider[modelClass]?.get() as T
    }
}