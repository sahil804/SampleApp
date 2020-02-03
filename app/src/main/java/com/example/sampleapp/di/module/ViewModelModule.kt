package com.example.sampleapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.data.RowsRepository
import com.example.sampleapp.data.database.AppDatabase
import com.example.sampleapp.data.network.ApiInterface
import com.example.sampleapp.di.MainViewModelFactory
import com.example.sampleapp.di.ViewModelKey
import com.example.sampleapp.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindUserViewModel(userViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory
}

