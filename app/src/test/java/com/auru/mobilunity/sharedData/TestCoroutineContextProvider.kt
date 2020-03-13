package com.auru.mobilunity.sharedData

import com.auru.mobilunity.utils.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestCoroutineContextProvider : CoroutineContextProvider() {
    override val Main: CoroutineContext = Dispatchers.Unconfined
    override val COMMON: CoroutineContext = Dispatchers.Unconfined
}