package com.baharudin.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.spacex.R
import com.baharudin.spacex.data.DragonResponse
import com.baharudin.spacex.data.DragonResponseItem
import com.baharudin.spacex.databinding.ItemDragonBinding
import com.bumptech.glide.Glide

class DragonAdapter: RecyclerView.Adapter<DragonAdapter.DragonViewHolder>() {

    private lateinit var contextAdapter : Context
    inner class DragonViewHolder(val binding : ItemDragonBinding) : RecyclerView.ViewHolder(binding.root)

    private var diffUtil = object  : DiffUtil.ItemCallback<DragonResponseItem>() {
        override fun areItemsTheSame(
            oldItem: DragonResponseItem,
            newItem: DragonResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DragonResponseItem,
            newItem: DragonResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
     val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonViewHolder {
        val inflater = ItemDragonBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,false
        )
        contextAdapter = parent.context
        return DragonViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: DragonViewHolder, position: Int) {
        val dragon = differ.currentList[position]
        holder.binding.apply {
            Glide.with(contextAdapter)
                .load(dragon.flickr_images[2])
                .into(ivDragon)
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}