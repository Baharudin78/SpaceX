package com.baharudin.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.spacex.data.RocketResponseItem
import com.baharudin.spacex.databinding.ItemRocketBinding
import com.bumptech.glide.Glide

class RocketAdapter : RecyclerView.Adapter<RocketAdapter.RocketHolder>() {
    private lateinit var contexAdapter : Context

    inner class RocketHolder(val binding : ItemRocketBinding) : RecyclerView.ViewHolder(binding.root)

    private var diffUtil = object : DiffUtil.ItemCallback<RocketResponseItem>() {
        override fun areItemsTheSame(
            oldItem: RocketResponseItem,
            newItem: RocketResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RocketResponseItem,
            newItem: RocketResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketHolder {
        val inflater = ItemRocketBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent, false
        )
        contexAdapter = parent.context
        return RocketHolder(inflater)
    }

    override fun onBindViewHolder(holder: RocketHolder, position: Int) {
        val rocket = differ.currentList[position]
        holder.binding.apply {
            Glide.with(contexAdapter)
                .load(rocket.flickr_images[0])
                .into(ivRocket)
            tvRocket.text = rocket.name
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}