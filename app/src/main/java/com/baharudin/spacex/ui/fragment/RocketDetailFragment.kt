package com.baharudin.spacex.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.baharudin.spacex.R
import com.baharudin.spacex.databinding.FragmentRocketDetailBinding
import com.baharudin.spacex.ui.MainActivity
import com.baharudin.spacex.ui.SpaceViewModel
import com.bumptech.glide.Glide

class RocketDetailFragment : Fragment(R.layout.fragment_rocket_detail) {

    private var _binding : FragmentRocketDetailBinding? = null
    private val binding get() = _binding!!
    val rocketArgs by navArgs<RocketDetailFragmentArgs>()
    private lateinit var rocketViewModel : SpaceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRocketDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        rocketViewModel = (activity as MainActivity).viewModel

        binding.apply {
            val rocket = rocketArgs.rocket
            Glide.with(this@RocketDetailFragment)
                .load(rocket.flickr_images[0])
                .into(ivRocket)
            tvNameRocket.text = rocket.name
            tvYears.text = rocket.first_flight
            tvCountry.text = rocket.country
            tvCompany.text = rocket.company
            tvDescriptio.text = rocket.description
        }
    }
}