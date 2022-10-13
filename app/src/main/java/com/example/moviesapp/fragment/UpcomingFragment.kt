package com.example.moviesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviesapp.databinding.FragmentUpcomingBinding
import org.koin.core.component.KoinComponent

class UpcomingFragment : Fragment(), KoinComponent {

    private lateinit var binding: FragmentUpcomingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = UpcomingFragment()
    }
}
