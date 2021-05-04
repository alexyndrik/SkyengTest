package com.alexyndrik.skyengtest.data.remote

import com.alexyndrik.skyengtest.data.remote.model.Meaning
import com.alexyndrik.skyengtest.data.remote.model.Word
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("words/search")
    fun getWords(
        @Query("search") search: String,
        @Query("page") page: Int?,
        @Query("pageSize") pageSize: Int?
    ): Flowable<Response<List<Word>>>

    @GET("meanings")
    fun getMeanings(
        @Query("ids") ids: String,
        @Query("updatedAt") updatedAt: String?
    ): Flowable<Response<List<Meaning>>>

}