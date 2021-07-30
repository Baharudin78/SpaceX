package com.baharudin.spacex.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.baharudin.spacex.R
import com.baharudin.spacex.databinding.FragmentDetailShipBinding
import com.baharudin.spacex.ui.MainActivity
import com.baharudin.spacex.ui.SpaceViewModel
import com.bumptech.glide.Glide

class ShipDetailFragment : Fragment(R.layout.fragment_detail_ship) {

    private var _binding : FragmentDetailShipBinding? = null
    private val binding get() = _binding!!
    val shipArgs by navArgs<ShipDetailFragmentArgs>()
    private lateinit var shipViewModel : SpaceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailShipBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        shipViewModel = (activity as MainActivity).viewModel
        binding.apply {
            val ship = shipArgs.ships
            Glide.with(this@ShipDetailFragment)
                .load(ship.image)
                .into(ivShip)
            tvHomeport.text = ship.home_port
            tvShip.text = ship.name
            tvYears.text = ship.year_built.toString()
            type.text = ship.type
        }


    }
}