package com.oxcoding.moviemvvm.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.coyote.data.network.CoyoteWebService
import com.coyote.data.db.tables.User
import com.coyote.testonnech.ui.users.list.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource (private val apiService : CoyoteWebService, private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, User>(){

    private var page = 1

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.fetchUsersFromRemote(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.results, null, page+1)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDataSource", it.message)
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.fetchUsersFromRemote(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if(10 >= params.key) { // 10 is max number of page
                            callback.onResult(it.results, params.key+1)
                            networkState.postValue(NetworkState.LOADED)
                        }
                        else{
                            networkState.postValue(NetworkState.ENDOFLIST)
                        }
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDataSource", it.message)
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

    }
}