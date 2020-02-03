package com.example.sampleapp.data.models

import com.google.gson.annotations.SerializedName

data class JsonModel(@SerializedName("title") val title : String,
                     @SerializedName("rows") val rows : List<Rows>)