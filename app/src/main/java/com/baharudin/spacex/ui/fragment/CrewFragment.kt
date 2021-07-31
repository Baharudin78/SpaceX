package com.baharudin.spacex.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.spacex.R
import com.baharudin.spacex.adapter.CrewAdapter
import com.baharudin.spacex.data.crew.CrewResponse
import com.baharudin.spacex.data.crew.CrewResponseItem
import com.baharudin.spacex.databinding.FragmentCrewBinding
import com.baharudin.spacex.ui.MainActivity
import com.baharudin.spacex.ui.SpaceViewModel
import com.baharudin.spacex.util.Resource

class CrewFragment : Fragment(R.layout.fragment_crew), CrewAdapter.OnClickItemListener {

    private var _binding : FragmentCrewBinding? = null
    private val binding get() = _binding!!
    private lateinit var crewAdapter : CrewAdapter
    private var dataCrew = ArrayList<CrewResponse>()
    private lateinit var shipViewModel: SpaceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentCrewBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        shipViewModel = (activity as MainActivity).viewModel

        shipViewModel.getAllCrew.observe(viewLifecycleOwner,  { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { resultCrew ->
                        setupRecycleview()
                        hideProgressBar()
                        dataCrew.add(resultCrew)
                        crewAdapter.differ.submitList(resultCrew)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.messege.let { messege ->
                        Log.e("crew","terdapat error $messege")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }
        private fun showProgressBar() {
        binding.progressBar2.visibility = View.VISIBLE
    }
        private fun hideProgressBar() {
        binding.progressBar2.visibility = View.INVISIBLE
    }
        private fun setupRecycleview() {
            crewAdapter = CrewAdapter(this)
            crewAdapter.notifyDataSetChanged()
            binding.recyclerView.apply {
            adapter = crewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onClickItem(crew: CrewResponseItem) {
        val action = CrewFragmentDirections.actionCrewFragmentToCrewDetailFragment(crew)
        findNavController().navigate(action)
    }
}