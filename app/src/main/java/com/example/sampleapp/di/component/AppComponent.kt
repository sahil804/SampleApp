package com.example.sampleapp.di.component

import android.app.Application
import com.example.sampleapp.App
import com.example.sampleapp.di.module.*
import com.example.sampleapp.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        BuilderModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}