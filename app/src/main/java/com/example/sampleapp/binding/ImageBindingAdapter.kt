package com.example.sampleapp.binding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.sampleapp.R

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImage(view:ImageView, url:String) {
        // The Json has null values for HREF, so displaying default image
        if(url == "Unknown Title") {
            Glide.with(view.context)
                .load(R.drawable.ic_launcher_foreground)
                .into(view)
        } else {
            Glide.with(view.context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(view)
        }
    }

}