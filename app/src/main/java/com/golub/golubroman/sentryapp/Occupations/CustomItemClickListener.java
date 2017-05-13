package com.golub.golubroman.sentryapp.Occupations;

import android.view.View;

/**
 * Created by roman on 12.05.17.
 */

public interface CustomItemClickListener {
    void onItemClick(View v, final OccupationsAdapter.OccupationsViewHolder viewHolder,
                     OccupationsAdapter adapter);
    void onLongItemClick(View v, final OccupationsAdapter.OccupationsViewHolder viewHolder,
                         OccupationsAdapter adapter);
}
