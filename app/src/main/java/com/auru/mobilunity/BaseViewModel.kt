package com.auru.mobilunity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected var job = SupervisorJob()
    protected var coroutineScope = CoroutineScope(Dispatchers.Main + job)


    protected fun getFreshScope(): CoroutineScope {
        coroutineScope.coroutineContext.cancelChildren()
        return coroutineScope
    }


    override fun onCleared() {
        super.onCleared()

        coroutineScope.coroutineContext.cancelChildren()

    }

}