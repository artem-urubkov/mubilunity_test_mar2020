package com.auru.mobilunity.injection

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModuleTest::class
    ]
)
interface TestAppComponent {
}
