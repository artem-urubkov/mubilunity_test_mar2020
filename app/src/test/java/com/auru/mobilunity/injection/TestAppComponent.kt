package com.auru.mobilunity.injection

import com.auru.mobilunity.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModuleTest::class
    ]
)
interface TestAppComponent {
//    fun inject(mainViewModel: MainViewModel)
}
