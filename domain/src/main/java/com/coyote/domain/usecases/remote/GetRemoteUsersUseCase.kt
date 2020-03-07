package com.coyote.domain.usecases.remote

import com.coyote.domain.usecases.remote.GetUsersRemoteGateway

class GetRemoteUsersUseCase(private val getUsersRemoteGateway: GetUsersRemoteGateway) {

    fun execute(page : Int)  {
          getUsersRemoteGateway.getRemoteUsers(page)
    }

}