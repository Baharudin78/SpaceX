package com.baharudin.spacex.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.spacex.R
import com.baharudin.spacex.adapter.ViewPagerAdapter
import com.baharudin.spacex.databinding.FragmentLauncherBinding
import com.google.android.material.tabs.TabLayoutMediator

class LauncherFragment : Fragment(R.layout.fragment_launcher) {

    private var _binding : FragmentLauncherBinding? = null
    private val binding get() = _binding!!
    private val upcoming = "Upcoming"
    private val latest = "Latest"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLauncherBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        val adapterLauncher = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapterLauncher

        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, possition ->
            when(possition) {
                0 -> {
                    tab.text = upcoming
                }
                1 -> {
                    tab.text = latest
                }
            }
        }.attach()
    }
}