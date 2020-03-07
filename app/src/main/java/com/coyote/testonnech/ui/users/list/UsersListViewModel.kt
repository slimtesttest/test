package com.coyote.testonnech.ui.users.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.coyote.data.db.tables.User
import com.coyote.domain.usecases.remote.GetRemoteUsersUseCase
import com.coyote.testonnech.ui.base.BaseViewModel
import com.coyote.testonnech.ui.users.list.repository.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UsersListViewModel(private val getRemoteUsersUseCase: GetRemoteUsersUseCase, private val userPagedListRepository: UserPagedListRepository) :
    BaseViewModel() {

    val listUsersLiveData = MutableLiveData<List<User>>()

    fun testViewModel() {
        Timber.tag("UsersListViewModel").d("slim koin slim ")
        val disposable1 = getRemoteUsersUseCase
            .execute().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ trips ->
                Timber.tag("TAG").d(trips[0].toString())
                listUsersLiveData.postValue(trips)
            }, { error ->
                //log  error
                Timber.tag("TAG").e(error.toString())
            })
        compositeDisposable.add(disposable1)
    }

    val  moviePagedList : LiveData<PagedList<User>> by lazy {
        userPagedListRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val  networkState : LiveData<NetworkState> by lazy {
        userPagedListRepository.getNetworkState()
    }
}
