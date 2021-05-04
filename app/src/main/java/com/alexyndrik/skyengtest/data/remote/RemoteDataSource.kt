package com.alexyndrik.skyengtest.data.remote

import com.alexyndrik.skyengtest.data.remote.model.Meaning
import com.alexyndrik.skyengtest.data.remote.model.Word
import io.reactivex.Flowable
import retrofit2.Response

class RemoteDataSource constructor(private val remoteService: RemoteService)  {

    fun getWords(
        search: String,
        page: Int?,
        pageSize: Int?
    ) : Flowable<Response<List<Word>>> = remoteService.getWords(search, page, pageSize)

    fun getMeanings(
        ids: String,
        updatedAt: String?
    ) : Flowable<Response<List<Meaning>>> = remoteService.getMeanings(ids, updatedAt)

}