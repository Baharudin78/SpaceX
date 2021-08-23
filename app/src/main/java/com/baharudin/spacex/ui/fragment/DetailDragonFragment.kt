package com.baharudin.spacex.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.baharudin.spacex.R
import com.baharudin.spacex.databinding.FragmentDetailDragonBinding
import com.baharudin.spacex.ui.MainActivity
import com.baharudin.spacex.ui.SpaceViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class DetailDragonFragment : Fragment(R.layout.fragment_detail_dragon) {

    private var _binding : FragmentDetailDragonBinding? = null
    private val binding get() = _binding!!
    val args by navArgs<DetailDragonFragmentArgs>()
    private lateinit var viewModel : SpaceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailDragonBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        binding.apply {
            val dragon = args.dragon
            Glide.with(this@DetailDragonFragment)
                .load(dragon.flickr_images[1])
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                }).into(ivDragon)
            tvNama.text = dragon.name
            type.text = dragon.type
            tvMasssa.text = dragon.dry_mass_kg.toString()
            tvYears.text = dragon.first_flight
            tvDescription.text = dragon.description
        }
    }
}