package com.coyote.data.mapper

import com.coyote.data.db.tables.UserEntity
import com.coyote.domain.model.User

object UserMapper {

    fun mapUserToDomain(userEntity: UserEntity) =
        User(
            userEntity.name,
            userEntity.email, userEntity.phone,
            userEntity.cell,
            userEntity.nationality,
            userEntity.gender, userEntity.picture
        )

    fun mapUserToEntity(user: User) =
        UserEntity(
            name = user.name,
            email = user.email,
            phone = user.phone,
            cell = user.cell,
            nationality = user.nationality,
            gender = user.gender,
            picture = user.picture
        )

    fun mapListUserToEntity(users: List<User>): List<UserEntity> {
        val listUserEntity = arrayListOf<UserEntity>()
        users.forEach {
            listUserEntity.add(mapUserToEntity(it))
        }
        return listUserEntity
    }
}

