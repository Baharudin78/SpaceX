package com.baharudin.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.spacex.data.ship.ShipResponseItem
import com.baharudin.spacex.databinding.ItemShipBinding
import com.bumptech.glide.Glide

class ShipAdapter : RecyclerView.Adapter<ShipAdapter.ShipViewHolder>() {

    private lateinit var contextAdapter : Context

    inner class ShipViewHolder(val binding: ItemShipBinding) : RecyclerView.ViewHolder(binding.root)

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
                .into(ivShip)
            tvNamaShip.text = ship.name
            tvTipe.text = ship.type
            tvTahunBuat.text = ship.year_built.toString()
            tvPelabuhan.text = ship.home_port
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}