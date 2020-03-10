package com.auru.mobilunity.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.auru.mobilunity.AndroidApp
import com.auru.mobilunity.BaseViewModel
import com.auru.mobilunity.dto.RepoElement
import com.auru.mobilunity.network.NetworkDataConverter
import com.auru.mobilunity.network.RetrofitRestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel(application: Application)  : BaseViewModel(application) {

    companion object {
        val LOG_TAG = MainViewModel::class.java.simpleName
    }

    @Inject
    lateinit var restApi: RetrofitRestService

    init{
        getApplication<AndroidApp>().component.inject(this)
    }


    /* ******************************** LiveData block start ******************************** */

    private val repoElementsLD = MutableLiveData<List<RepoElement>>()
    private val errorLD = MutableLiveData<String>()

    fun getRepoElementsLD() = repoElementsLD
    fun getErrorLD() = errorLD

    /* ******************************** LiveData block end ******************************** */


    fun refreshList() {
        getFreshScope().launch {
            try {
                val reposList = mutableListOf<RepoElement>()
                withContext(Dispatchers.Default) {
                    val resultList = restApi.getRepoElements().blockingGet()
                    reposList.addAll(resultList)
                }
                repoElementsLD.postValue(reposList)
            } catch (e: Exception) {
                val errorMsgId = NetworkDataConverter.convertRestErrorToMessageId(e)
                val message = getApplication<Application>().getString(errorMsgId)
                errorLD.postValue(message)
            }
        }
    }

}
