package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.domain.entity.Movie
import com.example.moviesapp.R
import com.example.moviesapp.databinding.CardViewNowPlayingBinding
import com.example.moviesapp.util.Constants

class NowPlayingAdapter(
    private val movies: List<Movie>,
    private val listener: MovieAdapterListener
) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_view_now_playing,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewNowPlayingBinding.bind(itemView)
        fun bind(item: Movie, listener: MovieAdapterListener) {
            binding.apply {
                this.cardViewMovieNameNowPlaying.text = item.title
                Glide.with(itemView.context)
                    .load(item.imgURL)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(Constants.IMAGE_RADIUS)))
                    .into(this.cardViewImageMovieNowPlaying)
                binding.cardViewNowPlaying.setOnClickListener {
                    listener.setOnClickListener(item.id)
                }
            }
        }
    }
}
