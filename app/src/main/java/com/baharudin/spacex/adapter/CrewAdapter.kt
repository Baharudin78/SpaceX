package com.baharudin.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.spacex.data.crew.CrewResponseItem
import com.baharudin.spacex.databinding.ItemCrewBinding
import com.bumptech.glide.Glide

class CrewAdapter : RecyclerView.Adapter<CrewAdapter.CrewHolder>() {

    private lateinit var contexts : Context

    class CrewHolder(val binding : ItemCrewBinding) : RecyclerView.ViewHolder(binding.root)

    private var diffUtil = object : DiffUtil.ItemCallback<CrewResponseItem>() {
        override fun areItemsTheSame(
            oldItem: CrewResponseItem,
            newItem: CrewResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CrewResponseItem,
            newItem: CrewResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewHolder {
        val inflater = ItemCrewBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,false
        )
        contexts = parent.context
        return CrewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CrewHolder, position: Int) {
        val crew = differ.currentList[position]
        holder.binding.apply {
            Glide.with(contexts)
                .load(crew.image)
                .into(ivCrew)
            tvNama.text = crew.name
            tvStatus.text = crew.status
            tvAgency.text = crew.agency
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}