package com.example.moviesapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.R
import com.example.moviesapp.adapter.PageAdapter
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.utils.Connection
import com.example.moviesapp.viewmodel.MainActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    private val viewModel: MainActivityViewModel by inject()
    private lateinit var binding: ActivityMainBinding
    private lateinit var pageAdapter: PageAdapter
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isConnected = Connection.isOnline(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = PageAdapter(this)
        pageAdapter = binding.viewPager.adapter as PageAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = pageAdapter.getPageTitle(position)
        }.attach()

        viewModel.getMovies(isConnected)
        viewModel.getMovieState().observe({ lifecycle }, ::updateUI)
    }

    private fun updateUI(moviesData: MainActivityViewModel.MoviesData) {
        when (moviesData.mState) {
            MainActivityViewModel.MoviesState.LOADING -> {
                showLoader()
            }
            MainActivityViewModel.MoviesState.EMPTY_DATABASE -> showEmptyState()
        }
    }

    private fun showLoader() {
        // this method will be implemented when we merge my work with Juli's
    }

    private fun showEmptyState() {
        binding.emptyState.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search -> {
            // this will be implemented in the ticket "Search feature"
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }
}
