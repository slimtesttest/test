package com.coyote.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.coyote.data.db.tables.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface TripDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrip(trip: UserEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserEntity>): Completable

    @Delete
    fun deleteUser(trip: UserEntity): Completable

    @Query("DELETE FROM UserEntity")
    fun deleteAllUser(): Completable

    @Query("SELECT * from UserEntity")
    fun selectAllUsers(): DataSource.Factory<Int,UserEntity>
}