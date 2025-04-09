package com.annalech.listofproducts.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetListShopListUseCase @Inject constructor (
    private val repository: ShopListRepository){
      fun getShopList(): LiveData<List<ShopItem>> {

        return repository.getShopList()
    }
}