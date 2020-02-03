package com.example.sampleapp.data

import android.util.Log
import com.example.sampleapp.data.database.AppDatabase
import com.example.sampleapp.data.models.JsonModel
import com.example.sampleapp.data.models.Rows
import com.example.sampleapp.data.network.ApiInterface
import com.example.sampleapp.ui.MainViewModel
import com.example.sampleapp.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

class RowsRepository @Inject constructor (val apiInterface: ApiInterface, val db: AppDatabase, val util: Utils) {

    private val TAG = RowsRepository::class.qualifiedName

    fun getRows():Observable<List<Rows>> {
        if(!util.isConnectedToNetwork()) {
            return getRowsFromDatabase()
        }
        return Observable.concatArrayEager(getRowsFromNetwork(), getRowsFromDatabase())




//        return Observable.fromCallable { rowsDao.all }
//            .concatMap { rowsList ->
//                if (rowsList.isEmpty()) {
//                    Log.d(TAG, " is Empty")
//                    apiInterface.getPosts()
//                        .concatMap {
//                            Log.d("Sahil", " --> " + it.rows)
//                            rowsDao.insertAll(*it.rows.toTypedArray())
//                            Observable.just(it.rows)
//                        }
//                } else Observable.just(rowsList)
//            }
    }

    private fun getRowsFromNetwork():Observable<List<Rows>> {
        return apiInterface.getPosts()
            .concatMap {
                db.rowsDao().insertAll(*it.rows.toTypedArray())
                Observable.just(it.rows)
            }

    }

    private fun getRowsFromDatabase():Observable<List<Rows>> {
        return db.rowsDao().all.toObservable()
    }

}