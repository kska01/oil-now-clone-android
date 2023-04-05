package com.example.oilnow.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.oilnow.R
import com.example.oilnow.adapter.NewsRecyclerViewAdapter
import com.example.oilnow.adapter.ViewPagerAadapter
import com.example.oilnow.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: MutableList<Int>
    private lateinit var adapter: ViewPagerAadapter
    private lateinit var newsRecyclerView: RecyclerView
    private var viewPagerPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()

        with(binding) {
            newsRecyclerView = oilNowNewsRecyclerview
            newsRecyclerView.adapter = NewsRecyclerViewAdapter()
        }
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }


    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable, 2500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewPager() {
        viewPager2 = binding.homeViewpager
        handler = Handler(Looper.myLooper()!!)
        imageList = mutableListOf(
            android.R.color.holo_red_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_green_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_purple
        )
        val originalListSize = imageList.size

        binding.homeViewpagerPageNumber.text =
            getString(R.string.viewpager_page_number, 1, originalListSize)

        adapter = ViewPagerAadapter(imageList, viewPager2)
        viewPager2.adapter = adapter
        viewPager2.clipToOutline = true

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                viewPagerPosition = position
                binding.homeViewpagerPageNumber.text =
                    getString(
                        R.string.viewpager_page_number,
                        viewPagerPosition % originalListSize + 1,
                        originalListSize
                    )

                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })
    }
}