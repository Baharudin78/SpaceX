package com.baharudin.spacex.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.baharudin.spacex.R
import com.baharudin.spacex.databinding.FragmentCompanyInfoBinding
import com.baharudin.spacex.ui.MainActivity
import com.baharudin.spacex.ui.SpaceViewModel
import com.baharudin.spacex.util.Resource

class CompanyInfoFragment : Fragment (R.layout.fragment_company_info) {

    private var _binding : FragmentCompanyInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var companViewModel : SpaceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentCompanyInfoBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        companViewModel = (activity as MainActivity).viewModel

        companViewModel.getCompanyInfo.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    binding.progressBar3.visibility = View.INVISIBLE
                    binding.tvCompanyName.text = response.data?.name
                    binding.tvFounder.text = response.data?.founder
                    binding.yearFounder.text = response.data?.founded.toString()
                    binding.tvEmploye.text = response.data?.employees.toString()
                    binding.tvValuasi.text = response.data?.valuation.toString()
                    binding.tvDesc.text = response.data?.summary
                }
                is Resource.Error -> {
                    binding.progressBar3.visibility = View.INVISIBLE
                    Log.e("company","error copany ")
                }
                is Resource.Loading -> {
                    binding.progressBar3.visibility = View.VISIBLE
                }
            }
        })
    }
}