package com.example.moviesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.Movie
import com.example.moviesapp.R
import com.example.moviesapp.adapter.TopRatedAdapter
import com.example.moviesapp.databinding.FragmentTopRatedBinding
import com.example.moviesapp.viewmodel.TopRatedViewModel
import org.koin.android.ext.android.inject

class TopRatedFragment : Fragment() {
    private lateinit var binding: FragmentTopRatedBinding
    private val viewModel: TopRatedViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieState().observe({ lifecycle }, ::updateUI)
        viewModel.getTopRatedMovies()
    }

    private fun updateUI(movieData: TopRatedViewModel.MovieData) {
        when (movieData.state) {
            TopRatedViewModel.MovieState.RESPONSE_SUCCESS -> showMovies(movieData.data)
            TopRatedViewModel.MovieState.RESPONSE_ERROR -> showError()
            TopRatedViewModel.MovieState.RESPONSE_LOADING -> showLoading()
        }
    }

    private fun showMovies(movieList: List<Movie>) {
        binding.topRatedLoader.visibility = View.GONE
        binding.topRatedImgEmptyState.visibility = View.GONE
        binding.topRatedRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.topRatedRecyclerView.adapter = TopRatedAdapter(movieList)
    }

    private fun showError() {
        binding.topRatedLoader.visibility = View.GONE
        binding.topRatedTextViewEmptyState.setText(R.string.empty_state_error)
        binding.topRatedImgEmptyState.setImageResource(R.drawable.no_recording)
    }

    private fun showLoading() {
        binding.topRatedLoader.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = TopRatedFragment()
    }
}
