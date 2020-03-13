package com.auru.mobilunity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.auru.mobilunity.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import javax.inject.Inject

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private var job = SupervisorJob()
    private var coroutineScope = CoroutineScope(Dispatchers.Main + job)

    @Inject
    lateinit var coroutinePool: CoroutineContextProvider

    protected open fun getFreshScope(): CoroutineScope {
        coroutineScope.coroutineContext.cancelChildren()
        coroutineScope = CoroutineScope(coroutinePool.Main + job)
        return coroutineScope
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.coroutineContext.cancelChildren()
    }

}