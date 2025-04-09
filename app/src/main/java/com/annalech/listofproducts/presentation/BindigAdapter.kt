package com.annalech.listofproducts.presentation

import androidx.databinding.BindingAdapter
import com.annalech.listofproducts.R
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("errorInputName")
fun bindInputErrorText(textInputLayout: TextInputLayout, isError: Boolean ){

    val message =   if(isError){
        textInputLayout.context.getString(R.string.error_input_name)
    } else{
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputCount")
fun bindInputErrorCount(textInputLayout: TextInputLayout, isError: Boolean ){

    val message =   if(isError){
        textInputLayout.context.getString(R.string.error_input_count)
    } else{
        null
    }
    textInputLayout.error = message
}
