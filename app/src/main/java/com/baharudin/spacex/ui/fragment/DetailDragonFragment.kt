package com.baharudin.spacex.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.spacex.R
import com.baharudin.spacex.databinding.FragmentDetailDragonBinding

class DetailDragonFragment : Fragment(R.layout.fragment_detail_dragon) {

    private var _binding : FragmentDetailDragonBinding? = null
    private val binging get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailDragonBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }
}