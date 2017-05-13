package com.golub.golubroman.sentry.AddOccupation.Icon_Spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.golub.golubroman.sentry.R;

/**
 * Created by roman on 12.05.17.
 */

public class SpinnerAdapter extends BaseAdapter {

    Context context;
    int icons[];
    String[] iconNames;
    LayoutInflater inflater;

    public SpinnerAdapter(Context applicationContext, int[] icons, String[] iconNames) {
        this.context = applicationContext;
        this.icons = icons;
        this.iconNames = iconNames;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return iconNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_spinner, null);
        ImageView icon = (ImageView) view.findViewById(R.id.image_spinner_item);
        TextView name = (TextView) view.findViewById(R.id.text_spinner_item);
        icon.setImageResource(icons[i]);
        name.setText(iconNames[i]);
        return view;
    }
}
