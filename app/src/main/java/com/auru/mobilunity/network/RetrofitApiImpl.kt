package com.auru.mobilunity.network

import android.webkit.MimeTypeMap
import com.auru.mobilunity.ui.main.RepoElement
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter

class RetrofitApiImpl(
    //todo  этот конвертер под вопросом - проверить можно ли без него обойтись
    errorConverter: Converter<ResponseBody, Error>,
    private val restApiService: RetrofitRestService
) : RetrofitApi {

    override fun getRepoElements(): Single<List<RepoElement>> {
        //доделать это, добавить обработку ошибок
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
