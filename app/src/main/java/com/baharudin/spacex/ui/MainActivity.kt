package com.baharudin.spacex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.baharudin.spacex.R
import com.baharudin.spacex.databinding.ActivityMainBinding
import com.baharudin.spacex.network.SpaceRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController
    lateinit var viewModel: SpaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spaceRepository = SpaceRepository()
        val viewModelProviderFactory = ViewModelFactory(spaceRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(SpaceViewModel::class.java)
        val navigation = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navigation.findNavController()

        binding.apply {
            bottomNavigationView.setupWithNavController(navController)
        }
    }
}