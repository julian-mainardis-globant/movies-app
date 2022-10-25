package com.example.moviesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Movie
import com.example.moviesapp.R
import com.example.moviesapp.adapter.UpcomingAdapter
import com.example.moviesapp.databinding.FragmentUpcomingBinding
import com.example.moviesapp.viewmodel.UpcomingViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class UpcomingFragment : Fragment(), KoinComponent {
    private lateinit var binding: FragmentUpcomingBinding
    private val viewModel: UpcomingViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieState().observe({ lifecycle }, ::updateUI)
        viewModel.getUpcomingMovies()
    }

    private fun updateUI(movieData: UpcomingViewModel.MovieData) {
        when (movieData.state) {
            UpcomingViewModel.MovieState.RESPONSE_SUCCESS -> showMovies(movieData.data)
            UpcomingViewModel.MovieState.RESPONSE_ERROR -> showError()
            UpcomingViewModel.MovieState.RESPONSE_LOADING -> showLoading()
        }
    }

    private fun showMovies(movieList: List<Movie>) {
        binding.loader.visibility = View.GONE
        binding.imgMain.visibility = View.GONE
        binding.upcomingHorizontalRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.upcomingHorizontalRecyclerView.adapter = UpcomingAdapter(movieList)
    }

    private fun showError() {
        binding.loader.visibility = View.GONE
        binding.textViewMain.setText(R.string.empty_state_error)
        binding.imgMain.setImageResource(R.drawable.no_recording)
    }

    private fun showLoading() {
        binding.loader.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = UpcomingFragment()
    }
}
