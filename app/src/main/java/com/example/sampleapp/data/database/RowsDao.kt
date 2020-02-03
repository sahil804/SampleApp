package com.example.sampleapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sampleapp.data.models.Rows

@Dao
interface RowsDao {

    @get:Query("SELECT * FROM Rows")
    val all: LiveData<List<Rows>>

    @Insert
    fun insertAll(vararg rows: Rows)

    @get:Query("DELETE FROM Rows")
    val deleteAll: Int

}