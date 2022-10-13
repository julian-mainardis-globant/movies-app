package com.example.moviesapp.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesapp.fragment.NowPlayingFragment
import com.example.moviesapp.fragment.TopRatedFragment
import com.example.moviesapp.fragment.UpcomingFragment
import com.example.moviesapp.utils.Constants.FIRST_POSITION
import com.example.moviesapp.utils.Constants.NUMBER_OF_PAGES
import com.example.moviesapp.utils.Constants.SECOND_POSITION
import com.example.moviesapp.utils.Constants.TAB_NOW_PLAYING
import com.example.moviesapp.utils.Constants.TAB_TOP_RATED
import com.example.moviesapp.utils.Constants.TAB_UPCOMING

class PageAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NUMBER_OF_PAGES

    override fun createFragment(position: Int) =
        when (position) {
            FIRST_POSITION -> NowPlayingFragment.newInstance()
            SECOND_POSITION -> TopRatedFragment.newInstance()
            else -> UpcomingFragment.newInstance()
        }

    fun getPageTitle(position: Int): String {
        when (position) {
            FIRST_POSITION -> {
                return TAB_NOW_PLAYING
            }
            SECOND_POSITION -> {
                return TAB_TOP_RATED
            }
            else -> {
                return TAB_UPCOMING
            }
        }
    }
}
