package com.auru.mobilunity.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.auru.mobilunity.utils.CoroutineContextProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import javax.inject.Inject

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var coroutinePool: CoroutineContextProvider

    protected open fun getFreshScope(): CoroutineScope {
        viewModelScope.coroutineContext.cancelChildren()
        return viewModelScope
    }

}