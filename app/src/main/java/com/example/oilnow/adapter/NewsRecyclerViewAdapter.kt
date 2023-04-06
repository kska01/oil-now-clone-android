package com.example.oilnow.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.oilnow.databinding.RecyclerviewOilNewsLayoutBinding
import com.example.oilnow.models.OilNews

class NewsRecyclerViewAdapter :
    ListAdapter<OilNews, NewsRecyclerViewAdapter.NewsViewHolder>(DiffCallback) {

    private lateinit var binding: RecyclerviewOilNewsLayoutBinding

    inner class NewsViewHolder(binding: RecyclerviewOilNewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            @SuppressLint("UseCompatLoadingForDrawables")
            fun bind(item: OilNews) {
                binding.apply {
                    newsImage.setImageResource(item.image)
                    newsTitle.text = itemView.context.getString(item.title)
                    newsTitle.background = itemView.context.getDrawable(item.background)
                    newsDescription.text = itemView.context.getString(item.description)
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        binding = RecyclerviewOilNewsLayoutBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsRecyclerViewAdapter.NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<OilNews>() {

            override fun areItemsTheSame(oldItem: OilNews, newItem: OilNews): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: OilNews, newItem: OilNews): Boolean {
                return oldItem.description == newItem.description
            }

        }
    }

}