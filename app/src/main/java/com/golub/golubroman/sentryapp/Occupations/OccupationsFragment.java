package com.golub.golubroman.sentryapp.Occupations;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.golub.golubroman.sentry.Occupations.OccupationsDatabase.DBQueries;
import com.golub.golubroman.sentry.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roman on 10.05.17.
 */

public class OccupationsFragment extends Fragment {

    @BindView(R.id.search_fragment_occupations) SearchView search_fragment_occupations;
    @BindView(R.id.recycler_fragment_occupations) RecyclerView occupationsRecycler;
    @BindView(R.id.fab_fragment_occupations) FloatingActionButton fab;

    private Handler handler;
    private OccupationsAdapter occupationsAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<OccupationsModel> occupationsList;
    private OnAddNewOccupationListener onAddNewOccupationListener;
    private OnDismissPopupMenuListener onDismissPopupMenuListener;
    private OnChosedOccupationListener onChosedOccupationListener;

    public interface OnChosedOccupationListener{
        void onChosedOccupation(OccupationsAdapter.OccupationsViewHolder vh,
                                OccupationsAdapter a);
    }
    public interface OnAddNewOccupationListener{
        void onAddNewOccupation();
    }
    public interface OnDismissPopupMenuListener{
        void onDismissPopupMenu();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onAddNewOccupationListener = (OnAddNewOccupationListener)activity;
        onChosedOccupationListener = (OnChosedOccupationListener)activity;
    }

    public static OccupationsFragment newInstance() {
        Bundle args = new Bundle();
        OccupationsFragment fragment = new OccupationsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_occupations, container, false);
        ButterKnife.bind(this, view);
        handler = new Handler();
        final Animation scaleAnimation = AnimationUtils.
                loadAnimation(getContext(), R.anim.scale_anim);
        occupationsList = DBQueries.getAppOccupations(getContext());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 100);
                v.startAnimation(scaleAnimation);
                onAddNewOccupationListener.onAddNewOccupation();
            }
        });
        occupationsAdapter = new OccupationsAdapter(occupationsList, occupationsRecycler,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, OccupationsAdapter.OccupationsViewHolder viewHolder, OccupationsAdapter adapter) {
                        onChosedOccupationListener.onChosedOccupation(viewHolder, adapter);
                    }

                    @Override
                    public void onLongItemClick(View v, OccupationsAdapter.OccupationsViewHolder viewHolder, OccupationsAdapter adapter) {
                        showPopupMenu(v, adapter, viewHolder);
                    }

                });
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        occupationsRecycler.setLayoutManager(gridLayoutManager);
        occupationsRecycler.setAdapter(occupationsAdapter);


        return view;
    }

    private void showPopupMenu(final View v, final OccupationsAdapter a, final OccupationsAdapter.OccupationsViewHolder vh) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.inflate(R.menu.context_menu);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.context_delete:
                                DBQueries.deleteAppOccupations(getContext(),
                                        ((OccupationsModel)(a.getOccupationsList()).get(vh.getPosition())).getId());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                onDismissPopupMenuListener = vh;
                onDismissPopupMenuListener.onDismissPopupMenu();
            }
        });
        popupMenu.show();
    }
}