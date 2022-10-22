package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Movie
import com.example.moviesapp.R
import com.example.moviesapp.databinding.CardViewUpcomingBinding

class UpcomingAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_view_upcoming,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewUpcomingBinding.bind(itemView)
        fun bind(item: Movie) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.imgURL)
                    .into(this.cardViewImageMovie)
            }
        }
    }
}
