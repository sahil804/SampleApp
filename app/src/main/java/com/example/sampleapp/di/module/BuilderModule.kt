package com.example.sampleapp.di.module

import com.example.sampleapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}