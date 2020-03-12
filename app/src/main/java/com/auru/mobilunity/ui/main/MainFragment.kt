package com.auru.mobilunity.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.auru.mobilunity.R
import com.auru.mobilunity.dto.RepoElement
import com.auru.mobilunity.widget.RecyclerViewEnum
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recAdapter: RecyclerAdapter

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getRepoElementsLD().observe(viewLifecycleOwner, Observer<List<RepoElement>> { elements ->
            val list = elements
            recAdapter.setItems(list ?: emptyList())
        })

        viewModel.getErrorLD().observe(viewLifecycleOwner, Observer<String> { errorMessage ->
            recyclerView.stateView = RecyclerViewEnum.EMPTY_STATE
            showErrorSnackBar(errorMessage)
        })

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recAdapter = RecyclerAdapter(emptyList<RepoElement>())

        recyclerView?.apply {
            layoutManager = linearLayoutManager
            emptyStateView = emptyView
            loadingStateView = loadingView
            adapter = recAdapter
        }

        recyclerView.stateView = RecyclerViewEnum.LOADING
        viewModel.refreshRepoData()

        swipe_refresh.setOnRefreshListener(OnRefreshListener {
            swipe_refresh.isRefreshing = false
            viewModel.refreshRepoData()
        })
    }

    private fun showErrorSnackBar(message: String) {
        val userDisabledSnackbar = Snackbar.make(coordinator_layout, message, Snackbar.LENGTH_INDEFINITE)
        userDisabledSnackbar.setAction(R.string.close, {})
        userDisabledSnackbar.setActionTextColor(ResourcesCompat.getColor(resources, R.color.yellow, null))
        userDisabledSnackbar.show()
    }

}
