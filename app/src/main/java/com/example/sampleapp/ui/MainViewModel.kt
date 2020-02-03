package com.example.sampleapp.ui

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
    val query : MutableLiveData<String> = MutableLiveData()
    var listRow: LiveData<Resource<List<Rows>>> = Transformations.switchMap(query) { query -> rowsRepo.loadRows() }

    fun start(start: String) {
        query.value = start
    }

    fun refresh() {
        query.value?.let {
            query.value = it
        }
    }

}