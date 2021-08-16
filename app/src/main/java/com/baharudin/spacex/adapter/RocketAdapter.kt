package com.baharudin.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.spacex.R
import com.baharudin.spacex.data.rocket.RocketResponseItem
import com.baharudin.spacex.databinding.ItemRocketBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class RocketAdapter(private var listener : OnRocketClickListener) : RecyclerView.Adapter<RocketAdapter.RocketHolder>() {
    private lateinit var contexAdapter : Context

    inner class RocketHolder(val binding : ItemRocketBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val itemRocket = differ.currentList[position]
                    if (itemRocket != null) {
                        listener.OnClickListener(itemRocket)
                    }
                }
            }
        }
    }

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
                .placeholder(R.drawable.circiular)
                .error(R.drawable.errorre)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(ivRocket)
            tvRocket.text = rocket.name
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnRocketClickListener {
        fun OnClickListener(rocket : RocketResponseItem)
    }
}