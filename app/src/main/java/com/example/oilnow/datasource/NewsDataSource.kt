package com.example.oilnow.datasource

import com.example.oilnow.R
import com.example.oilnow.models.OilNews

class NewsDataSource {
    fun loadNewsData(): MutableList<OilNews> {
        return mutableListOf(
            OilNews(
                android.R.color.holo_blue_bright,
                R.string.story_of_oilNow,
                R.string.story_description
            ),
            OilNews(
                android.R.color.white,
                R.string.event,
                R.string.event_description
            ),
            OilNews(
                android.R.color.white,
                R.string.news,
                R.string.news_description
            ),
            OilNews(
                android.R.color.darker_gray,
                R.string.utilization,
                R.string.util_description_1
            ),
            OilNews(
                android.R.color.white,
                R.string.utilization,
                R.string.util_description_2
            )
        )
    }
}