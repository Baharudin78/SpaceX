package com.baharudin.spacex.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.spacex.R
import com.baharudin.spacex.adapter.DragonAdapter
import com.baharudin.spacex.adapter.ShipAdapter
import com.baharudin.spacex.data.dragon.DragonResponse
import com.baharudin.spacex.data.dragon.DragonResponseItem
import com.baharudin.spacex.data.ship.ShipResponse
import com.baharudin.spacex.data.ship.ShipResponseItem
import com.baharudin.spacex.databinding.FragmentHomeBinding
import com.baharudin.spacex.ui.MainActivity
import com.baharudin.spacex.ui.SpaceViewModel
import com.baharudin.spacex.util.Resource

class HomeFragment : Fragment(R.layout.fragment_home), DragonAdapter.OnClickItemListener, ShipAdapter.OnItemClickListener{

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : SpaceViewModel
    private lateinit var dragonAdapter : DragonAdapter
    private lateinit var shipAdapter : ShipAdapter
    private var dataList = ArrayList<DragonResponse>()
    private var dataShip = ArrayList<ShipResponse>()

    private val SIGNS = "ERROR"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        dragonAdapter = DragonAdapter(this)



        viewModel.getAllDragon.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    hideShimmer()
                    response.data?.let { dragonResponse ->
                        setRecycleView()
                        hideShimmer()
                        dataList.add(dragonResponse)
                        Log.d("hasil","$dragonResponse")
                        dragonAdapter.differ.submitList(dragonResponse)

                    }
                }
                is Resource.Error -> {
                    hideShimmer()
                    response.messege?.let { messege ->
                        Log.e(SIGNS,"error di $messege")
                    }
                }
                is Resource.Loading -> {
                    showShimmer()
                }
            }
        })
        viewModel.getAllShip.observe(viewLifecycleOwner,  {response ->
            when(response) {
                is Resource.Success -> {
                    hideShimmer()
                    response.data?.let { shipResponse ->
                        setRecycleViewShip()
                        dataShip.add(shipResponse)
                        dataShip.random()
                        Log.d(SIGNS,"$shipResponse")
                        shipAdapter.differ.submitList(shipResponse)

                    }
                }
                is Resource.Error -> {
                    hideShimmer()
                    response.messege?.let { messege ->
                        Log.e(SIGNS,"error di $messege")
                    }
                }
                is Resource.Loading -> {
                    showShimmer()
                }
            }
        })
    }
    private fun hideShimmer (){
        binding.shimmerLoading.stopShimmer()
        binding.shimmerLoading.visibility = View.GONE
    }
    private fun showShimmer(){
        binding.shimmerLoading.startShimmer()
        binding.shimmerLoading.visibility = View.GONE
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setRecycleView() {
        dragonAdapter = DragonAdapter(this)
        dragonAdapter.notifyDataSetChanged()
        binding.rvDragon.apply {
            adapter = dragonAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRecycleViewShip() {
        shipAdapter = ShipAdapter(this)
        shipAdapter.notifyDataSetChanged()
        binding.rvShip.apply {
            adapter = shipAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun OnItemClick(dragon: DragonResponseItem) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailDragonFragment(dragon)
        findNavController().navigate(action)
    }

    override fun onCLick(ship: ShipResponseItem) {
        val toShip = HomeFragmentDirections.actionHomeFragmentToShipDetailFragment(ship)
        findNavController().navigate(toShip)
    }

}