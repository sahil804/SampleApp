package com.example.sampleapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.AppExecutors
import com.example.sampleapp.data.database.AppDatabase
import com.example.sampleapp.data.models.Resource
import com.example.sampleapp.data.models.Rows
import com.example.sampleapp.data.network.NetworkRepository
import javax.inject.Inject

class RowsRepository @Inject constructor(
    val networkRepository: NetworkRepository,
    val db: AppDatabase,
    val appExecutors: AppExecutors
) {

    private val TAG = RowsRepository::class.qualifiedName
    private var liveData:MutableLiveData<Resource<List<Rows>>> = MutableLiveData()


    fun loadRows(): LiveData<Resource<List<Rows>>> {
        return object : NetworkBoundResource<List<Rows>, List<Rows>>(appExecutors) {
            override fun saveCallResult(item: List<Rows>) {
                db.rowsDao().deleteAll
                db.rowsDao().insertAll(*item.toTypedArray())

            }

            override fun shouldFetch(data: List<Rows>?): Boolean {
                // For the demo App, fetching everytime from n/w
                //return data == null || data.isEmpty()
                return true
            }

            override fun loadFromDb(): LiveData<List<Rows>> {
                return db.rowsDao().all
            }

            override fun createCall(): LiveData<Resource<List<Rows>>> {
                return networkRepository.getRows()
            }

        }.asLiveData()
    }

    fun clearRows(): LiveData<Resource<List<Rows>>> {
        appExecutors.diskIO().execute {
            db.rowsDao().deleteAll
        }
        liveData.value = Resource.error("cleared Data", ArrayList())
        return liveData
    }

    fun getTitle():LiveData<String> {
        return networkRepository.liveDataTile
    }

}