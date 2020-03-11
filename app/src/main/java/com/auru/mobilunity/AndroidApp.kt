package com.auru.mobilunity

import android.app.Application
import com.auru.mobilunity.injection.AppComponent
import com.auru.mobilunity.injection.AppModule
import com.auru.mobilunity.injection.DaggerAppComponent
import com.auru.mobilunity.injection.DataModule

open class AndroidApp : Application() {

    var component: AppComponent = //by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule(baseUrl))
            .build()


}
