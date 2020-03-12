package com.auru.mobilunity.injection

import com.auru.mobilunity.AndroidApp
import com.auru.mobilunity.network.RetrofitRestService
import javax.inject.Inject

class InjectedInstancesHolder(application: AndroidApp) {

    @Inject
    lateinit var restApi: RetrofitRestService

    init {
        application.component.inject(this)
    }
}