package com.example.sampleapp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.data.RowsRepository
import com.example.sampleapp.data.database.AppDatabase
import com.example.sampleapp.data.database.RowsDao
import com.example.sampleapp.data.models.JsonModel
import com.example.sampleapp.data.models.Rows
import com.example.sampleapp.data.network.ApiClient
import com.example.sampleapp.data.network.ApiInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.math.log

class MainViewModel @Inject constructor(val rowsRepo: RowsRepository) : ViewModel() {

    private val TAG = MainViewModel::class.qualifiedName
    //val apiInterface: ApiInterface? = ApiClient.build()
    private lateinit var subscription: Disposable
    lateinit var mainListAdapter: MainActivityAdapter
    //var rowsDao: RowsDao
    val listRow: MutableLiveData<List<Rows>> = MutableLiveData()

    init {
        //rowsDao = db.rowsDao()
        getPost()
    }


    private fun getPost() {
        subscription = rowsRepo.getRows()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onRetrievePostListSuccess(it) }
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrievePostListSuccess(rows: List<Rows>) {
        listRow.value = rows
        //mainListAdapter.updateList(rows)
    }

}