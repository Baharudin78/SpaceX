package com.baharudin.spacex.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.spacex.R
import com.baharudin.spacex.adapter.DragonAdapter
import com.baharudin.spacex.adapter.ShipAdapter
import com.baharudin.spacex.data.dragon.DragonResponse
import com.baharudin.spacex.data.ship.ShipResponse
import com.baharudin.spacex.databinding.FragmentHomeBinding
import com.baharudin.spacex.ui.MainActivity
import com.baharudin.spacex.ui.SpaceViewModel
import com.baharudin.spacex.util.Resource

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : SpaceViewModel
    private lateinit var dragonAdapter : DragonAdapter
    private lateinit var shipAdapter : ShipAdapter
    private var dataList = ArrayList<DragonResponse>()
    private var dataShip = ArrayList<ShipResponse>()

    private var Tasd ="DragonRespon"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel


        viewModel.getAllDragon.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgeressbar()
                    response.data?.let { dragonResponse ->
                        setRecycleView()
                        hideProgeressbar()
                        dataList.add(dragonResponse)
                        Log.d("hasil","$dragonResponse")
                        dragonAdapter.differ.submitList(dragonResponse)

                    }
                }
                is Resource.Error -> {
                    hideProgeressbar()
                    response.messege?.let { messege ->
                        Log.e(Tasd,"error di $messege")
                    }
                }
                is Resource.Loading -> {
                    showProgressbar()
                }
            }
        })
        viewModel.getAllShip.observe(viewLifecycleOwner, Observer {response ->
            when(response) {
                is Resource.Success -> {
                    hideProgeressbar()
                    response.data?.let { shipResponse ->
                        setRecycleViewShip()
                        dataShip.add(shipResponse)
                        dataShip.random()
                        Log.d("hasil","$shipResponse")
                        shipAdapter.differ.submitList(shipResponse)

                    }
                }
                is Resource.Error -> {
                    hideProgeressbar()
                    response.messege?.let { messege ->
                        Log.e(Tasd,"error di $messege")
                    }
                }
                is Resource.Loading -> {
                    showProgressbar()
                }
            }
        })

    }
    private fun hideProgeressbar (){
        binding.progressBar.visibility = View.INVISIBLE
    }
    private fun showProgressbar(){
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun setRecycleView() {
        dragonAdapter = DragonAdapter()
        dragonAdapter.notifyDataSetChanged()
        binding.rvDragon.apply {
            adapter = dragonAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setRecycleViewShip() {
        shipAdapter = ShipAdapter()
        shipAdapter.notifyDataSetChanged()
        binding.rvShip.apply {
            adapter = shipAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}