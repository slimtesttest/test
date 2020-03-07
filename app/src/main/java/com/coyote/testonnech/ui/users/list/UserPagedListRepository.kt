package com.coyote.testonnech.ui.users.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.coyote.data.network.CoyoteWebService
import com.coyote.data.db.tables.User
import com.coyote.testonnech.ui.users.list.repository.MovieDataSourceFactory
import com.coyote.testonnech.ui.users.list.repository.NetworkState
import com.oxcoding.moviemvvm.data.repository.MovieDataSource
import io.reactivex.disposables.CompositeDisposable

class UserPagedListRepository(private val apiService: CoyoteWebService) {

    lateinit var moviePagedList: LiveData<PagedList<User>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<User>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()

            .setEnablePlaceholders(true)
            .setPageSize(POST_PER_PAGE)
            .setInitialLoadSizeHint(30)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap(moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }

    val POST_PER_PAGE = 30
}