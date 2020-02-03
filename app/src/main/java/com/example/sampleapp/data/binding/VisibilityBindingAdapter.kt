package com.example.sampleapp.binding

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter

object VisibilityBindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
       view.visibility = if(show) View.VISIBLE else View.GONE
    }
}