package com.auru.mobilunity.presentation.controllers.mainscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.auru.mobilunity.AndroidApp
import com.auru.mobilunity.dto.RepoElement
import com.auru.mobilunity.network.NetworkDataConverter
import com.auru.mobilunity.network.RetrofitRestService
import com.auru.mobilunity.presentation.BaseViewModel
import com.auru.mobilunity.presentation.viewutils.LCEResult
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

    private val repoElementsResultLD = MutableLiveData<LCEResult>()
    fun getRepoElementsResultLD(): LiveData<LCEResult> = repoElementsResultLD

    /* ******************************** LiveData block end ******************************** */

    fun refreshRepoData() {
        getFreshScope().launch {
            try {
                repoElementsResultLD.postValue(LCEResult.Loading)
                val reposList = mutableListOf<RepoElement>()
                withContext(coroutinePool.COMMON) {
                    val resultList = restApi.getRepoElements()
                    reposList.addAll(resultList)
                }
                Log.d(LOG_TAG, "reposList.size=${reposList.size}; setting to repoElementsLD")
                repoElementsResultLD.postValue(LCEResult.Success(reposList))
            } catch (e: Exception) {
                if (isActive) { // in case of JobCancellationException don't post+show error on UI
                    Log.d(LOG_TAG, "caught $e")
                    val errorMsgId = NetworkDataConverter.convertRestErrorToMessageId(e)
                    val message = getApplication<Application>().getString(errorMsgId)
                    repoElementsResultLD.postValue(LCEResult.Failure(message))
                }
            }
        }
    }

}
