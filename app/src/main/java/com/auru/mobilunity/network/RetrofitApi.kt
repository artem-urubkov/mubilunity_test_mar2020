package com.auru.mobilunity.network

import com.auru.mobilunity.ui.main.RepoElement
import io.reactivex.Single

interface RetrofitApi {
    fun getRepoElements(): Single<List<RepoElement>>
}