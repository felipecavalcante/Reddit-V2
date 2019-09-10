package com.example.redditv2.app.base.adapters

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @BindingAdapter("visible")
    @JvmStatic
    fun setVisible(view: View, visibility:Boolean){
        view.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}