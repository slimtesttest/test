package com.coyote.testonnech.ui.users.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.coyote.data.db.tables.User
import com.coyote.testonnech.R
import com.travelcar.test.ui.main.car.list.UserListClickEventListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), UserListClickEventListener {

    private val filter: String = ""
    private lateinit var listUsersAdapter: ListUsersAdapter
    private val postViewModel: UsersListViewModel by viewModel()
    private lateinit var list: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listUsersAdapter =
            ListUsersAdapter(this, this)
        cars_recycler.layoutManager = LinearLayoutManager(this)
        cars_recycler.adapter = listUsersAdapter

       /* postViewModel.testViewModel()

        postViewModel.listUsersLiveData.observe(this, Observer<List<User>> {
            list = it
            listUsersAdapter.setData(
                it.filter { user -> user.name.last.contains(filter, true) },
                filter
            )
        })*/

        postViewModel.moviePagedList.observe(this, Observer {
            listUsersAdapter.submitList(it)
        })

        postViewModel.networkState.observe(this, Observer {

        })


        /*search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                listUsersAdapter.setData(
                    list.filter { user -> user.name.last.contains(s.toString(), true) },
                    s.toString()
                )
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })*/
    }

    override fun onUserItemClick(user: User, sharedElementView: AppCompatImageView) {
    }
}
