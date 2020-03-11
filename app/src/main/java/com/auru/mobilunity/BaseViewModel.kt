package com.auru.mobilunity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    //TODO refactor for usage with Dispatchers.Main provided from Dagger

    protected var job = SupervisorJob()
    protected var coroutineScope = CoroutineScope(Dispatchers.Main + job)


    protected open fun getFreshScope(): CoroutineScope {
        coroutineScope.coroutineContext.cancelChildren()
        return coroutineScope
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScope.coroutineContext.cancelChildren()

    }

}