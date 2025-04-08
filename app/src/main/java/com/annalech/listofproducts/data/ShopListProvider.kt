package com.annalech.listofproducts.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.annalech.listofproducts.domain.ShopItem
import com.annalech.listofproducts.presentation.ShopApp
import javax.inject.Inject

class ShopListProvider : ContentProvider() {

    private val component by lazy {
        (context as ShopApp).components
    }

    @Inject
    lateinit var shopListDao: ShopListDao
    @Inject
    lateinit var mapper:ShopListItemMapper

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("com.annalech.listofproducts","shop_items", GET_SHOP_ITEM_QUERY )
    }


    override fun onCreate(): Boolean {
        component.inject(this)
      return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

      val x =  when(uriMatcher.match(uri)){
            GET_SHOP_ITEM_QUERY -> {
                shopListDao.getShopListCursor()
            }
            else->null
        }
        return x
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
       when(uriMatcher.match(uri)){
            GET_SHOP_ITEM_QUERY -> {
                if (values==null)return null
                val id = values.getAsInteger("id")
                val count = values.getAsInteger("count")
                val name = values.getAsString("name")
                val enabled = values.getAsBoolean("enabled")
                shopListDao.addShopItemProvide(
                    mapper.mapEntityToDbModel(
                        ShopItem(
                            name,
                            count,
                            enabled,
                            id)
                    )
                )
            }
        }


    return null

}

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        when(uriMatcher.match(uri)){
            GET_SHOP_ITEM_QUERY -> {
                val id = selectionArgs?.get(0)?.toInt() ?:-1
              return  shopListDao.deleteShopItemSync(id)
            }

        }
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }


    companion object{
        const val GET_SHOP_ITEM_QUERY = 101
    }
}