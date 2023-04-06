package com.example.oilnow.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.oilnow.R
import com.example.oilnow.adapter.NewsRecyclerViewAdapter
import com.example.oilnow.adapter.ViewPagerAadapter
import com.example.oilnow.databinding.FragmentHomeBinding
import com.example.oilnow.datasource.NewsDataSource
import com.example.oilnow.models.OilNews

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: MutableList<Int>
    private lateinit var viewPagerAdapter: ViewPagerAadapter
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter
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

        val newsDataSource: MutableList<OilNews> = NewsDataSource().loadNewsData()

        initViewPager()

//        binding.stickyScrollView.run {
//            header = binding.homeStickyHeader
//            stickListener = { _ -> }
//            freeListener = { _ -> }
//        }

        with(binding) {
            newsRecyclerView = oilNowNewsRecyclerview
            newsRecyclerViewAdapter = NewsRecyclerViewAdapter()
            newsRecyclerViewAdapter.submitList(newsDataSource)
            newsRecyclerView.adapter = newsRecyclerViewAdapter
            newsRecyclerView.layoutManager = LinearLayoutManager(requireContext()).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
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

        viewPagerAdapter = ViewPagerAadapter(imageList, viewPager2)
        viewPager2.adapter = viewPagerAdapter
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