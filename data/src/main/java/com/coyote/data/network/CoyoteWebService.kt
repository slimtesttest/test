package com.coyote.data.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface CoyoteWebService {

    @GET("api/?results=30&seed=abc")
    fun fetchUsersFromRemote(@Query("page") page : Int): Observable<WsUserListResponse>
}