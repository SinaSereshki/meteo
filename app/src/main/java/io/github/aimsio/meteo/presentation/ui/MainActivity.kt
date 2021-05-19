package io.github.aimsio.meteo.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.aimsio.meteo.R

class MainActivity : AppCompatActivity(), MainNavigator {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}