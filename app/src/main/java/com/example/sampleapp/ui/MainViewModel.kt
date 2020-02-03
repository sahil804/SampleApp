package com.example.sampleapp.ui

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.sampleapp.data.models.Resource
import com.example.sampleapp.data.repository.RowsRepository
import com.example.sampleapp.data.models.Rows
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(val rowsRepo: RowsRepository) : ViewModel() {

    private val TAG = MainViewModel::class.qualifiedName
    //val apiInterface: ApiInterface? = ApiClient.build()
    private lateinit var subscription: Disposable
    //var rowsDao: RowsDao
    val query : MutableLiveData<String> = MutableLiveData()
    private val reloadTrigger = MutableLiveData<Boolean>()
    var listRow: LiveData<Resource<List<Rows>>> = Transformations.switchMap(reloadTrigger) { rowsRepo.loadRows() }

    init {
        refreshRows()
    }

    fun refreshRows() {
        reloadTrigger.value = true
    }

    fun refresh() {
        query.value?.let {
            query.value = it
        }
    }

    fun start(start: String) {
        query.value = start
    }



//    init {
//        //rowsDao = db.rowsDao()
//        listRow = rowsRepo.loadRows()
//    }


//    fun getPost(): LiveData<Resource<List<Rows>>>  {
//        //listRow.value =  rowsRepo.loadRows()
//    }
//        subscription = rowsRepo.getRows()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { onRetrievePostListSuccess(it) }
//        return rowsRepo.loadRows()
//    }

    override fun onCleared() {
        super.onCleared()
        //subscription.dispose()
    }

    private fun onRetrievePostListSuccess(rows: List<Rows>) {
        //listRow.value = rows
        //mainListAdapter.updateList(rows)
    }

}