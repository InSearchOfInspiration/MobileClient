package com.golub.golubroman.sentry.Statistics;

import android.view.View;

import com.golub.golubroman.sentry.Occupations.OccupationsAdapter;

/**
 * Created by roman on 12.05.17.
 */

public interface CustomItemClickListener {
    void onItemClick(View v, final StatisticsAdapter.StatisticsViewHolder viewHolder,
                     StatisticsAdapter adapter);
}
