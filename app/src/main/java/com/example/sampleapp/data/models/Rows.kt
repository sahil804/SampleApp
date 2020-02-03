package com.example.sampleapp.data.models

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Rows (
    @PrimaryKey(autoGenerate = true)
    @Expose(deserialize = false, serialize = false) val id: Int,
    @SerializedName("title") val title : String?,
    @SerializedName("description")  val description : String?,
    @SerializedName("imageHref")  val imageHref : String?

)