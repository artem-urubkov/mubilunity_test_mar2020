package com.auru.mobilunity.network

import com.auru.mobilunity.dto.RepoElement
import retrofit2.http.GET

interface RetrofitRestService {

    @GET(reposUrl)
    suspend fun getRepoElements(): Array<RepoElement>

}