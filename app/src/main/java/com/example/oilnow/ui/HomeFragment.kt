package com.example.oilnow.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.oilnow.R
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

        viewPagerInit()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })
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

    private fun viewPagerInit() {
        viewPager2 = binding.homeViewpager
        handler = Handler(Looper.myLooper()!!)
        imageList = mutableListOf(
            android.R.color.holo_red_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_green_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_purple
        )

        adapter = ViewPagerAadapter(imageList, viewPager2)
        viewPager2.adapter = adapter
        viewPager2.background = ContextCompat.getDrawable(requireContext(), R.drawable.viewpager2_round_corner)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

}