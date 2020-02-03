package com.example.sampleapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.sampleapp.data.models.Resource
import com.example.sampleapp.data.models.Rows
import com.example.sampleapp.data.repository.RowsRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(val rowsRepo: RowsRepository) : ViewModel() {

    private val TAG = MainViewModel::class.qualifiedName


    private val reloadTrigger = MutableLiveData<Boolean>()
    val title:LiveData<String> = rowsRepo.getTitle()

    // reload true means reload from n/w, false means clear everything
    var listRow: LiveData<Resource<List<Rows>>> = Transformations.switchMap(reloadTrigger) { reload ->
        if(reload) {
            rowsRepo.loadRows()
        } else {
            rowsRepo.clearRows()
        }
    }

    init {
        reloadRows()
    }

    fun reloadRows() {
        reloadTrigger.value = true
    }

    fun clearRows() {
        reloadTrigger.value = false
    }
}