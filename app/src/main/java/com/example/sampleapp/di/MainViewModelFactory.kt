package com.example.sampleapp.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.data.RowsRepository
import com.example.sampleapp.data.database.AppDatabase
import com.example.sampleapp.data.network.ApiClient
import com.example.sampleapp.data.network.ApiInterface
import com.example.sampleapp.ui.MainViewModel
import javax.inject.Inject


class MainViewModelFactory @Inject constructor (val rowsRepo: RowsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(rowsRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}