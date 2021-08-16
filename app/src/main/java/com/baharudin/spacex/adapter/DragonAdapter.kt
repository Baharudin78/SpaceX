package com.baharudin.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.spacex.R
import com.baharudin.spacex.data.dragon.DragonResponseItem
import com.baharudin.spacex.databinding.ItemDragonBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DragonAdapter(private val listener : OnClickItemListener) : RecyclerView.Adapter<DragonAdapter.DragonViewHolder>() {

    private lateinit var contextAdapter : Context

    inner class DragonViewHolder(val binding : ItemDragonBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = differ.currentList[position]
                    if (item != null) {
                        listener.OnItemClick(item)
                    }
                }
            }
        }
    }

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
                .placeholder(R.drawable.circiular)
                .error(R.drawable.errorre)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(ivDragon)
            tvNamaDragon.text = dragon.name



        }
    }
    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    interface OnClickItemListener{
        fun OnItemClick(dragon : DragonResponseItem)
    }


}