package com.example.moviesapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.R
import com.example.moviesapp.adapter.PageAdapter
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.viewmodel.MainActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    private val viewModel: MainActivityViewModel by inject()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getMainActivityState().observe({ lifecycle }, ::updateUI)
        viewModel.initialize()
    }

    private fun updateUI(mainActivityData: MainActivityViewModel.MainActivityData) {
        when (mainActivityData.state) {
            MainActivityViewModel.MainActivityState.INITIALIZED -> initialize()
        }
    }

    private fun initialize() {
        binding.viewPager.adapter = PageAdapter(this)
        val pageAdapter = binding.viewPager.adapter as PageAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setIcon(arrayOf(R.drawable.ic_now_playing, R.drawable.ic_top_rated, R.drawable.ic_upcoming)[position])
            tab.text = pageAdapter.getPageTitle(position)
        }.attach()
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
