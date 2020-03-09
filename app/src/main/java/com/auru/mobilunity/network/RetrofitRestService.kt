package com.auru.mobilunity.network

import com.auru.mobilunity.reposUrl
import com.auru.mobilunity.ui.main.RepoElement
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitRestService {

    @GET(reposUrl)
    fun getRepoElements(): Single<List<RepoElement>>

}