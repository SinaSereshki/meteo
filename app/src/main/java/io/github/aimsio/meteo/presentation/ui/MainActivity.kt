package io.github.aimsio.meteo.presentation.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import io.github.aimsio.meteo.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(), MainNavigator {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}