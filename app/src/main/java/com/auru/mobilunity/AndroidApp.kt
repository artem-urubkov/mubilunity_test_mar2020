package com.auru.mobilunity

import android.app.Application
import com.auru.mobilunity.injection.AppComponent
import com.auru.mobilunity.injection.AppModule
import com.auru.mobilunity.injection.DaggerAppComponent
import com.auru.mobilunity.injection.DataModule

class AndroidApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule(baseUrl))
            .build()
    }

}
