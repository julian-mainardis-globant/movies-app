package com.example.moviesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.domain.entity.MovieDetail
import com.example.domain.util.Constants.MOVIE_ID
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMovieDetailBinding
import com.example.moviesapp.viewmodel.DetailViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MovieDetailFragment : DialogFragment(), KoinComponent {

    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel: DetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        viewModel.getMovieDetailState().observe({ lifecycle }, ::updateUI)
        viewModel.getMovieDetailById(arguments?.getString(MOVIE_ID).orEmpty())
        binding.movieDetailBackButton.setOnClickListener { viewModel.onBackButtonPressed() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    private fun updateUI(movieDetailData: DetailViewModel.MovieDetailData) {
        when (movieDetailData.state) {
            DetailViewModel.MovieDetailState.RESPONSE_SUCCESS -> showMovieDetails(movieDetailData.data)
            DetailViewModel.MovieDetailState.RESPONSE_ERROR -> showError()
            DetailViewModel.MovieDetailState.RESPONSE_LOADING -> showLoading()
            DetailViewModel.MovieDetailState.BACK_BUTTON_PRESSED -> dismiss()
        }
    }

    private fun showMovieDetails(movieDetail: MovieDetail?) {
        movieDetail.let {
            binding.movieDetailLoader.visibility = View.GONE
            binding.movieDetailImgEmptyState.visibility = View.GONE
            binding.apply {
                movieDetailTitle.text = it?.title
                movieDetailHomepage.text = it?.homepage
                movieDetailOverview.text = it?.overview
                if (it?.tagline.isNullOrEmpty()) {
                    this.movieDetailTagline.visibility = View.GONE
                } else {
                    movieDetailTagline.text = it?.tagline
                }
                movieDetailReleaseDate.text = it?.releaseDate
                movieVoteAverage.text = it?.voteAverage.toString()
                movieDetailGenres.text = it?.genres.toString()
                Glide.with(this@MovieDetailFragment)
                    .load(it?.imgURL)
                    .into(movieDetailImage)
            }
        }
    }

    private fun showError() {
        binding.movieDetailLoader.visibility = View.GONE
        binding.movieDetailTextViewEmptyState.setText(R.string.empty_state_error)
        binding.movieDetailImgEmptyState.setImageResource(R.drawable.no_recording)
    }

    private fun showLoading() {
        binding.movieDetailLoader.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance(movieId: String): MovieDetailFragment {
            val args = Bundle()
            args.putString(MOVIE_ID, movieId)
            val fragment = MovieDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
