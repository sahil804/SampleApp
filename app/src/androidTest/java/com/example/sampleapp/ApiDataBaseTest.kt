package com.example.sampleapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.sampleapp.data.database.AppDatabase
import com.example.sampleapp.data.models.Rows
import com.example.sampleapp.data.network.ApiInterface
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiDataBaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase


    @Before
    fun createDb() {
      db =  Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
          AppDatabase::class.java).build()
    }

    @After
    fun stopService() {
        db.close()
    }

    @Test
    fun insertAndRead() {
        val rows = arrayListOf(Rows(1, "title1","desc1","url1"),
            Rows(2, "title2","desc2","url2"))
        db.rowsDao().insertAll(*rows.toTypedArray())
        val rowsList = db.rowsDao().all.value
        rowsList?.forEach {
            assertThat(it.title, notNullValue())
            assertThat(it.description, notNullValue())
            assertThat(it.imageHref, notNullValue())
        }
    }

    @Test
    fun checkDelete() {
        val rows = arrayListOf(Rows(1, "title1","desc1","url1"),
            Rows(2, "title2","desc2","url2"))
        db.rowsDao().insertAll(*rows.toTypedArray())
        val expected = 2
        assert(db.rowsDao().deleteAll.equals(expected))
    }

}