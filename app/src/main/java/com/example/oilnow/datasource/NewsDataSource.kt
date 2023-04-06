package com.example.oilnow.datasource

import com.example.oilnow.R
import com.example.oilnow.models.OilNews

class NewsDataSource {
    fun loadNewsData(): MutableList<OilNews> {
        return mutableListOf(
            OilNews(
                android.R.color.holo_blue_bright,
                R.string.story_of_oilNow,
                R.drawable.title_background_story_shape,
                R.string.story_description
            ),
            OilNews(
                R.color.event_background,
                R.string.event,
                R.drawable.title_background_event_shape,
                R.string.event_description
            ),
            OilNews(
                android.R.color.white,
                R.string.news,
                R.drawable.title_background_news_shape,
                R.string.news_description
            ),
            OilNews(
                android.R.color.darker_gray,
                R.string.utilization,
                R.drawable.title_background_util_shape,
                R.string.util_description_1
            ),
            OilNews(
                android.R.color.darker_gray,
                R.string.utilization,
                R.drawable.title_background_util_shape,
                R.string.util_description_2
            )
        )
    }
}