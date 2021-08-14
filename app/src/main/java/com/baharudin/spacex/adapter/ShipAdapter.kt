package com.baharudin.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.spacex.R
import com.baharudin.spacex.data.ship.ShipResponseItem
import com.baharudin.spacex.databinding.ItemShipBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ShipAdapter(private val listener : OnItemClickListener) : RecyclerView.Adapter<ShipAdapter.ShipViewHolder>() {

    private lateinit var contextAdapter : Context

    inner class ShipViewHolder(val binding: ItemShipBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = differ.currentList[position]
                    if (item != null) {
                        listener.onCLick(item)
                    }
                }
            }
        }
    }

    private var diffUtil = object : DiffUtil.ItemCallback<ShipResponseItem>(){
        override fun areItemsTheSame(
            oldItem: ShipResponseItem,
            newItem: ShipResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ShipResponseItem,
            newItem: ShipResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipViewHolder {
        val inflater = ItemShipBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent, false
        )
        contextAdapter = parent.context
        return ShipViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ShipViewHolder, position: Int) {
        val ship = differ.currentList[position]
        holder.binding.apply {
            Glide.with(contextAdapter)
                .load(ship.image)
                .placeholder(R.drawable.circiular)
                .error(R.drawable.errorre)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(ivShip)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnItemClickListener {
        fun onCLick(ship : ShipResponseItem)
    }

}