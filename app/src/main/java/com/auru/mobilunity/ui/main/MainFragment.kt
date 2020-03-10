package com.auru.mobilunity.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.auru.mobilunity.R

import androidx.lifecycle.Observer

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.auru.mobilunity.dto.RepoElement
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

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
//            hideLoading() - not so needed because the loading is versy fast - thus, in most cases will lead to screen-blinking ->
//            omitted for to process it in good manner will take not little time
            val list = elements
            adapter.setItems(list ?: emptyList())
        })

        viewModel.getErrorLD().observe(viewLifecycleOwner, Observer<String> { errorMessage ->
            showErrorSnackBar(errorMessage)
        })

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(emptyList<RepoElement>())
        recyclerView.adapter = adapter

        viewModel.refreshRepoData()
    }

    private fun showErrorSnackBar(message: String) {
        val userDisabledSnackbar = Snackbar.make(coordinator_layout, message, Snackbar.LENGTH_INDEFINITE)
        userDisabledSnackbar.setAction(R.string.close, {})
        userDisabledSnackbar.setActionTextColor(ResourcesCompat.getColor(resources, R.color.yellow, null))
        userDisabledSnackbar.show()
    }

}
