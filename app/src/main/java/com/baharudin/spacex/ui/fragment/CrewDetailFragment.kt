package com.baharudin.spacex.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.baharudin.spacex.R
import com.baharudin.spacex.databinding.FragmentCrewDetailBinding
import com.baharudin.spacex.ui.MainActivity
import com.baharudin.spacex.ui.SpaceViewModel
import com.bumptech.glide.Glide

class CrewDetailFragment : Fragment(R.layout.fragment_crew_detail) {

    private var _binding : FragmentCrewDetailBinding? = null
    private val binding get() = _binding!!
    private val crewArgs by navArgs<CrewDetailFragmentArgs>()
    private lateinit var crewViewModel : SpaceViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentCrewDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        crewViewModel = (activity as MainActivity).viewModel

        binding.apply {
            val crew = crewArgs.crew
            Glide.with(this@CrewDetailFragment)
                .load(crew.image)
                .into(imageView)
            tvNama.text = crew.name
            tvStatus.text = crew.status
            tvAgency.text = crew.agency
            tvWikipedia.text = crew.wikipedia
        }
    }
}