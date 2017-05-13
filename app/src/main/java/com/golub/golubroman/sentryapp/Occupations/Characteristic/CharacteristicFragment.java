package com.golub.golubroman.sentry.Characteristics;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golub.golubroman.sentry.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by roman on 10.05.17.
 */

public class CharacteristicsFragment extends Fragment {

    @BindView(R.id.name_fragment_characteristics) TextView occupation_name;
    @BindView(R.id.seekbar_time_fragment_characteristics) DiscreteSeekBar time_seekbar;
    @BindView(R.id.seekbar_usefulness_fragment_characteristics) DiscreteSeekBar usefulness_seekbar;
    @BindView(R.id.seekbar_pleasure_fragment_characteristics) DiscreteSeekBar pleasure_seekbar;
    @BindView(R.id.seekbar_fatigue_fragment_characteristics) DiscreteSeekBar fatigue_seekbar;
    @BindView(R.id.reveal_fragment_characteristic) RelativeLayout myView;
    @BindView(R.id.fab_choose_fragment_characteristic) FloatingActionButton fabChoose;

    public static CharacteristicsFragment newInstance() {
        Bundle args = new Bundle();
        CharacteristicsFragment fragment = new CharacteristicsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_occupation_characteristics, container, false);
        ButterKnife.bind(this, view);
        fabChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButton(fabChoose);

            }
        });
        return view;
    }

    private void animateButton(final ImageButton mFloatingButton) {
        mFloatingButton.animate().translationXBy(0.5f).translationY(150).translationXBy(-0.9f)
                .translationX(-150). setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animateReavel((int) mFloatingButton.getX(), 150,mFloatingButton);
            }
        });
    }

    private void animateReavel(int cx, int cy, final ImageButton mFloatingButton) {
        float finalRadius = hypo(myView.getWidth(), myView.getHeight());
        SupportAnimator animator =
                ViewAnimationUtils.createCircularReveal(myView, cx + 100, cy + 1500, 0, finalRadius);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                mFloatingButton.setVisibility(View.INVISIBLE);
                myView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationEnd() {
            }
            @Override
            public void onAnimationCancel() {
            }
            @Override
            public void onAnimationRepeat() {
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(700);
        animator.start();
    }

    static float hypo(int a, int b) {
        return (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
}