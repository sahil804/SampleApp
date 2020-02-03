package com.example.sampleapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.sampleapp.AppExecutors
import com.example.sampleapp.data.database.AppDatabase
import com.example.sampleapp.data.models.Resource
import com.example.sampleapp.data.models.Rows
import com.example.sampleapp.data.network.NetworkRepository
import com.example.sampleapp.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

class RowsRepository @Inject constructor(
    val networkRepository: NetworkRepository,
    val db: AppDatabase,
    val appExecutors: AppExecutors
) {

    private val TAG = RowsRepository::class.qualifiedName

    fun loadRows(): LiveData<Resource<List<Rows>>> {
        return object : NetworkBoundResource<List<Rows>, List<Rows>>(appExecutors) {
            override fun saveCallResult(item: List<Rows>) {
                Log.d(TAG, "rows: " + item)
                db.rowsDao().insertAll(*item.toTypedArray())

            }

            override fun shouldFetch(data: List<Rows>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Rows>> {
                Log.d(TAG, "loadFromDb: ")
                return db.rowsDao().all
            }

            override fun createCall(): LiveData<Resource<List<Rows>>> {
                return networkRepository.getRows()
            }

        }.asLiveData()
    }

//    fun getRows():Observable<List<Rows>> {
//        if(!util.isConnectedToNetwork()) {
//            return getRowsFromDatabase()
//        }
//        return Observable.concatArrayEager(getRowsFromNetwork(), getRowsFromDatabase())
//
//
//
//
////        return Observable.fromCallable { rowsDao.all }
////            .concatMap { rowsList ->
////                if (rowsList.isEmpty()) {
////                    Log.d(TAG, " is Empty")
////                    apiInterface.getPosts()
////                        .concatMap {
////                            Log.d("Sahil", " --> " + it.rows)
////                            rowsDao.insertAll(*it.rows.toTypedArray())
////                            Observable.just(it.rows)
////                        }
////                } else Observable.just(rowsList)
////            }
//    }

    private fun getRowsFromNetwork(): Observable<List<Rows>>? {
//        return apiInterface.getPosts()
//            .concatMap {
//                db.rowsDao().insertAll(*it.rows.toTypedArray())
//                Observable.just(it.rows)
//            }
        return null

    }

//    private fun getRowsFromDatabase():Observable<List<Rows>> {
//        return db.rowsDao().all.toObservable()
//    }

}