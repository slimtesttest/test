package com.coyote.domain.usecases.local

import com.coyote.domain.usecases.remote.GetUsersRemoteGateway

class GetLocalUsersUseCase(private val getUsersRemoteGateway: GetUsersRemoteGateway) {

    fun execute()  {
          getUsersRemoteGateway.getRemoteUsers()
    }

}