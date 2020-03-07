package com.coyote.domain.usecases.remote

import com.coyote.domain.model.User
import io.reactivex.Single

interface GetUsersRemoteGateway {
    fun getRemoteUsers(page : Int)
}