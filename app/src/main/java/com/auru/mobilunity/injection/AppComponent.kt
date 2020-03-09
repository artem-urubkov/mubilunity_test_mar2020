package com.auru.mobilunity.injection

import com.auru.mobilunity.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class
    ]
)
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
}
