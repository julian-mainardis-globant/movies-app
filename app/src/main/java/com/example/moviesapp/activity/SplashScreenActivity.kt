package com.example.moviesapp.activity

import android.animation.Animator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.databinding.SplashScreenBinding
import com.example.moviesapp.mvvm.viewmodel.SplashScreenViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashScreenActivity : AppCompatActivity(), KoinComponent {
    private lateinit var binding: SplashScreenBinding
    val viewModel: SplashScreenViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.splashScreenState.observe({ lifecycle }, ::changingState)
        viewModel.startAnimation()
    }

    private fun changingState(SplashScreenData: SplashScreenViewModel.SplashScreenData) {
        when (SplashScreenData.state) {
            SplashScreenViewModel.SplashScreenState.START -> setListener()
            SplashScreenViewModel.SplashScreenState.DONE -> launchMainActivity()
        }
    }

    private fun setListener() {
        val animatorListener = object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) = viewModel.splashDone()
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        }
        binding.lottieSplashScreen.addAnimatorListener(animatorListener)
    }

    private fun launchMainActivity() {
        startActivity(MainActivity.newInstance(this))
        finish()
    }
}
