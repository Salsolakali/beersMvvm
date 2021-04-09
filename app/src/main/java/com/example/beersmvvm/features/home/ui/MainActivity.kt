package com.example.beersmvvm.features.home.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beersmvvm.R
import com.example.beersmvvm.core.domain.RequestFailure
import com.example.beersmvvm.core.extensions.doIfFailure
import com.example.beersmvvm.core.extensions.doIfInProgress
import com.example.beersmvvm.core.extensions.doIfSuccess
import com.example.beersmvvm.core.presentation.Navigator
import com.example.beersmvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var homeViewModel: HomeDataViewModel

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var beerAdapter: BeerAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUI()
        homeViewModel.fetchBeers()
    }


    private fun initUI() {
        setupObservers()
        initSearchView()
        initRecycler()
        initListeners()
    }

    private fun setupObservers() {
        homeViewModel.beersResult.observe(this) { result ->

            result.doIfSuccess { items ->
                hideLoading()
                beerAdapter.collection = items
            }

            result.doIfFailure {
                hideLoading()
                manageError(it)
            }

            result.doIfInProgress {
                showLoading()
            }

        }
    }

    private fun initSearchView() {
        binding.searchBarProfiles.onActionViewExpanded()
        binding.searchBarProfiles.isFocusable = false
        binding.searchBarProfiles.clearFocus()
        binding.searchBarProfiles.queryHint = resources.getString(R.string.search)
    }

    private fun initRecycler() {
        binding.recyclerBeers.layoutManager = LinearLayoutManager(this)
        binding.recyclerBeers.adapter = beerAdapter
    }

    private fun initListeners() {
        binding.searchBarProfiles.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                showLoading()
                homeViewModel.fetchBeersFiltered(s)
                return false
            }
        })
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun manageError(requestFailure: RequestFailure) {
        val message = when (requestFailure) {
            is RequestFailure.ApiError -> requestFailure.message
            is RequestFailure.NoConnectionError -> getString(R.string.connection_error_message)
            is RequestFailure.UnknownError -> getString(R.string.default_error_message)
        }
        if (message.isNullOrEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}