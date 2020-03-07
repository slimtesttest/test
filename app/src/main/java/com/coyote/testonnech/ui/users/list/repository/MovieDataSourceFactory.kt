package com.coyote.testonnech.ui.users.list.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.coyote.data.network.CoyoteWebService
import com.coyote.data.db.tables.User
import com.oxcoding.moviemvvm.data.repository.MovieDataSource
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val apiService : CoyoteWebService, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, User>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, User> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}