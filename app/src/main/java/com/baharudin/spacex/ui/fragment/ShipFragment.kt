package com.baharudin.spacex.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.spacex.R
import com.baharudin.spacex.adapter.RocketAdapter
import com.baharudin.spacex.data.RocketResponse
import com.baharudin.spacex.databinding.FragmentShipBinding
import com.baharudin.spacex.ui.MainActivity
import com.baharudin.spacex.ui.SpaceViewModel
import com.baharudin.spacex.util.Resource

class ShipFragment : Fragment(R.layout.fragment_ship) {

    private var _binding : FragmentShipBinding? = null
    private val binding get() = _binding!!
    private lateinit var shipViewModel : SpaceViewModel
    private lateinit var rocketAdapter: RocketAdapter
    private val dataList = ArrayList<RocketResponse>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentShipBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        shipViewModel = (activity as MainActivity).viewModel

        shipViewModel.getAllRocket.observe(viewLifecycleOwner,  { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { rocketResponse ->
                        setRecycleView()
                        hideProgressBar()
                        dataList.add(rocketResponse)
                        rocketAdapter.differ.submitList(rocketResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.messege.let { rocketMessege ->
                        Log.e("rocket","Error di $rocketMessege")
                    }
                }
                is Resource.Loading -> {
                    showProgressbar()
                }
            }

        })


    }
    private fun showProgressbar() {
        binding.progressBar2.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar2.visibility = View.INVISIBLE
    }
    private fun setRecycleView() {
        rocketAdapter = RocketAdapter()
        rocketAdapter.notifyDataSetChanged()
        binding.recyclerView.apply {
            adapter = rocketAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}