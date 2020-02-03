package com.example.sampleapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sampleapp.data.models.Rows
import io.reactivex.Single

@Dao
interface RowsDao {

    @get:Query("SELECT * FROM Rows")
    val all: Single<List<Rows>>

    @Insert
    fun insertAll(vararg rows: Rows)

}