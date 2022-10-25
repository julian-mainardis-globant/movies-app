package com.example.moviesapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.entity.Movie
import com.example.moviesapp.R
import com.example.moviesapp.adapter.SearchAdapter
import com.example.moviesapp.databinding.ActivitySearchBinding
import com.example.moviesapp.util.Constants
import com.example.moviesapp.viewmodel.SearchActivityViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchActivity : AppCompatActivity(), KoinComponent {
    private val viewModel: SearchActivityViewModel by inject()
    private lateinit var binding: ActivitySearchBinding
    private var movieList: List<Movie> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getSearchState().observe({ lifecycle }, ::updateUI)
        viewModel.getMovies()
        setListener()
    }

    private fun updateUI(SearchActivityData: SearchActivityViewModel.SearchData) {
        when (SearchActivityData.state) {
            SearchActivityViewModel.SearchState.LOADING -> displayLoader()
            SearchActivityViewModel.SearchState.SUCCESS -> {
                this.movieList = SearchActivityData.data
                displayMovies(SearchActivityData.data)
            }
            SearchActivityViewModel.SearchState.ERROR -> displayError()
            SearchActivityViewModel.SearchState.FILTERED -> displayMovies(SearchActivityData.data)
        }
    }

    private fun displayLoader() {
        binding.searchInput.visibility = View.GONE
        binding.searchLoader.visibility = View.VISIBLE
    }

    private fun displayMovies(movieList: List<Movie>) {
        binding.searchInput.visibility = View.VISIBLE
        binding.searchLoader.visibility = View.GONE
        binding.textEmptyState.visibility = View.GONE
        binding.imgEmptyState.visibility = View.GONE
        binding.searchRecyclerView.layoutManager = GridLayoutManager(this, Constants.COLUMNS_AMOUNT)
        binding.searchRecyclerView.adapter = SearchAdapter(movieList)
    }

    private fun displayError() {
        binding.searchLoader.visibility = View.GONE
        binding.searchInput.visibility = View.GONE
        binding.textEmptyState.setText(R.string.empty_state_error)
        binding.imgEmptyState.setImageResource(R.drawable.no_recording)
    }

    private fun setListener() {
        binding.searchInputText.addTextChangedListener {
            viewModel.filter(it.toString(), movieList)
        }
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, SearchActivity::class.java)
    }
}
