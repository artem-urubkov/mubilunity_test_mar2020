package com.auru.mobilunity.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auru.mobilunity.R

import androidx.lifecycle.Observer

import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getRepoElementsLD().observe(viewLifecycleOwner, Observer<List<RepoElement>> { elements ->
            //            Timber.d("$LOG_TAG, onRegistrationNeededLD()")
//            hideLoading()
            val list = elements
            val i = 0
            //todo
        })

        viewModel.getErrorLD().observe(viewLifecycleOwner, Observer<String> { errorMessage ->
//            processException(exception)
            val msg = message
            val i = 0
            //todo
        })
    }

}
