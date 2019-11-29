package com.miracozkan.challange01.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.miracozkan.challange01.R
import com.miracozkan.challange01.datalayer.local.ProjectDatabase
import com.miracozkan.challange01.datalayer.remote.RetrofitClient
import com.miracozkan.challange01.utils.DependencyUtil
import com.miracozkan.challange01.utils.Status.*
import com.miracozkan.challange01.utils.ViewModelFactory
import com.miracozkan.challange01.utils.hide
import com.miracozkan.challange01.utils.show
import com.miracozkan.challange01.vm.ProjectViewModel
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment : Fragment() {

    private val projectRepository by lazy {
        DependencyUtil.getProjectRepository(
            RetrofitClient().getRetrofitClient(),
            ProjectDatabase.getInstance(context!!).projectDao()
        )
    }

    private val projectViewModel by lazy {
        ViewModelProviders.of(
            this,
            ViewModelFactory(projectRepository)
        ).get(ProjectViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val action = SplashFragmentDirections.actionSplashFragmentToMainFragment()
        runObserver(action)
    }

    private fun runObserver(action: NavDirections) {
        projectViewModel.projectDataStatus.observe(this, Observer { _result ->
            when (_result) {
                SUCCESS -> {
                    splashFragmentProgressBar.hide()
                    findNavController().navigate(action)
                }
                ERROR -> {
                    splashFragmentProgressBar.hide()
                    findNavController().navigate(action)
                    Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
                }
                LOADING -> {
                    splashFragmentProgressBar.show()
                }
                else -> {
                    Toast.makeText(activity, "ELSE", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
