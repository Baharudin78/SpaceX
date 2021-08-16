package com.baharudin.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.spacex.R
import com.baharudin.spacex.data.crew.CrewResponseItem
import com.baharudin.spacex.databinding.ItemCrewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class CrewAdapter(private val listener : OnClickItemListener) : RecyclerView.Adapter<CrewAdapter.CrewHolder>() {

    private lateinit var contexts : Context

    inner class CrewHolder(val binding : ItemCrewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
           binding.root.setOnClickListener {
               val position = adapterPosition
               if (position != RecyclerView.NO_POSITION) {
                   val item = differ.currentList[position]
                   if (item != null) {
                       listener.onClickItem(item)
                   }
               }
           }
        }
    }

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
                .placeholder(R.drawable.circiular)
                .error(R.drawable.errorre)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivCrew)
            tvNama.text = crew.name
            tvStatus.text = crew.status
            tvAgency.text = crew.agency
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    interface OnClickItemListener {
        fun onClickItem(crew : CrewResponseItem)
    }

}