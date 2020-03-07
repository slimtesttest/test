package com.coyote.domain.usecases.local

import androidx.paging.DataSource
import com.coyote.domain.model.User
import io.reactivex.Single

interface GetUsersLocalGateway {
    fun getRemoteUsers() : DataSource.Factory<Int,User>
}