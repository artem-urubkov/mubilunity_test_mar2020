package com.auru.mobilunity.presentation.controllers.mainscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.auru.mobilunity.AndroidApp
import com.auru.mobilunity.presentation.BaseViewModel
import com.auru.mobilunity.dto.RepoElement
import com.auru.mobilunity.network.NetworkDataConverter
import com.auru.mobilunity.network.RetrofitRestService
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {

    companion object {
        val LOG_TAG = MainViewModel::class.java.simpleName
    }

    @Inject
    lateinit var restApi: RetrofitRestService

    init {
        getApplication<AndroidApp>().component.inject(this)
    }


    /* ******************************** LiveData block start ******************************** */

    private val repoElementsLD = MutableLiveData<List<RepoElement>>()
    private val errorLD = MutableLiveData<String>()

    fun getRepoElementsLD(): LiveData<List<RepoElement>> = repoElementsLD
    fun getErrorLD(): LiveData<String> = errorLD

    /* ******************************** LiveData block end ******************************** */

    fun refreshRepoData() {
        getFreshScope().launch {
            try {
                val reposList = mutableListOf<RepoElement>()
                withContext(coroutinePool.COMMON) {
                    val resultList = restApi.getRepoElements()
                    reposList.addAll(resultList)
                }
                Log.d(LOG_TAG, "reposList.size=${reposList.size}; setting to repoElementsLD")
                repoElementsLD.postValue(reposList)
            } catch (e: Exception) {
                if (isActive) { // in case of JobCancellationException don't post+show error on UI
                    Log.d(LOG_TAG, "caught $e")
                    val errorMsgId = NetworkDataConverter.convertRestErrorToMessageId(e)
                    val message = getApplication<Application>().getString(errorMsgId)
                    errorLD.postValue(message)
                }
            }
        }
    }

}
