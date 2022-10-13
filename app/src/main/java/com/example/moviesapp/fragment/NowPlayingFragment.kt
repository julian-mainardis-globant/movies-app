package com.example.moviesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.entity.Movie
import com.example.moviesapp.R
import com.example.moviesapp.adapter.NowPlayingAdapter
import com.example.moviesapp.databinding.FragmentNowPlayingBinding
import com.example.moviesapp.util.Constants
import com.example.moviesapp.viewmodel.NowPlayingViewModel
import org.koin.android.ext.android.inject

class NowPlayingFragment : Fragment() {

    private lateinit var binding: FragmentNowPlayingBinding
    private val viewModel: NowPlayingViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieState().observe({ lifecycle }, ::updateUI)
        viewModel.getNowPlayingMovies()
    }

    private fun updateUI(movieData: NowPlayingViewModel.MovieData) {
        when (movieData.state) {
            NowPlayingViewModel.MovieState.RESPONSE_SUCCESS -> showMovies(movieData.data)
            NowPlayingViewModel.MovieState.RESPONSE_ERROR -> showError()
            NowPlayingViewModel.MovieState.RESPONSE_LOADING -> showLoading()
        }
    }

    private fun showMovies(movieList: List<Movie>) {
        binding.nowPlayingLoader.visibility = View.GONE
        binding.imgMain.visibility = View.GONE
        binding.nowPlayingRecyclerView.layoutManager = GridLayoutManager(context, Constants.COLUMNS_AMOUNT)
        binding.nowPlayingRecyclerView.adapter = NowPlayingAdapter(movieList)
    }

    private fun showError() {
        binding.nowPlayingLoader.visibility = View.GONE
        binding.textViewMain.setText(R.string.empty_state_error)
        binding.imgMain.setImageResource(R.drawable.no_recording)
    }

    private fun showLoading() {
        binding.nowPlayingLoader.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = NowPlayingFragment()
    }
}
