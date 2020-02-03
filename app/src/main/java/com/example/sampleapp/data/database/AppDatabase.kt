package com.example.sampleapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleapp.data.models.Rows

@Database(
    entities = [Rows::class],
    version = 1
)
//Not using this class, as Room instance is created by Dagger
abstract class AppDatabase : RoomDatabase(){
    abstract fun rowsDao(): RowsDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "sample.db")
            .build()
    }
}