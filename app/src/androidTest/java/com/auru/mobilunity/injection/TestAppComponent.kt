package com.auru.mobilunity.injection

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModuleInstrumentedTest::class
    ]
)
interface InstrumentedTestAppComponent {
}
