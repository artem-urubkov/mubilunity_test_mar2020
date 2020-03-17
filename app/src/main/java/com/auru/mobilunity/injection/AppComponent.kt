package com.auru.mobilunity.injection

import com.auru.mobilunity.presentation.controllers.mainscreen.MainViewModel
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
    fun inject(injectedInstancesHolder: InjectedInstancesHolder)
}
