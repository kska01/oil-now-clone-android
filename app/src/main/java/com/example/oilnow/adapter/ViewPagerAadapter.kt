package com.example.oilnow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.oilnow.databinding.ViewpagerItemLayoutBinding

class ViewPagerAadapter(
    private val imageList: MutableList<Int>,
    private val viewPager2: ViewPager2
) :
    RecyclerView.Adapter<ViewPagerAadapter.ImageViewHolder>() {

    private lateinit var binding: ViewpagerItemLayoutBinding

    class ImageViewHolder(private var binding: ViewpagerItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Int) {
                binding.apply {
                    viewpagerImageView.setImageResource(item)
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAadapter.ImageViewHolder {
        binding = ViewpagerItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerAadapter.ImageViewHolder, position: Int) {
        val item = imageList[position]
        holder.bind(item)

        if (position == imageList.size - 1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()

    }
}