package com.golub.golubroman.sentry.AddOccupation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.*;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.golub.golubroman.sentry.AddOccupation.Icon_Spinner.SpinnerAdapter;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.golub.golubroman.sentry.Occupations.OccupationsModel;
import com.golub.golubroman.sentry.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by roman on 13.05.17.
 */

public class AddOccupationFragment extends Fragment implements AddOccupationMVP.VtPInterface, AdapterView.OnItemSelectedListener{

    @BindView(R.id.fab_done_fragment_add) android.support.design.widget.FloatingActionButton fabDone;
    @BindView(R.id.edit_name_fragment_add) EditText name_edit;
    @BindView(R.id.reveal_fragment_add) RelativeLayout myView;
    @BindView(R.id.spinner_icon_fragment_add) Spinner icon_spinner;
    @BindView(R.id.edit_color_fragment_add) TextView color_chooser;
    @BindView(R.id.seekbar_usefulness_fragment_add) DiscreteSeekBar usefulness_seekbar;
    @BindView(R.id.seekbar_pleasure_fragment_add) DiscreteSeekBar pleasure_seekbar;
    @BindView(R.id.seekbar_fatigue_fragment_add) DiscreteSeekBar fatigue_seekbar;

    int[] icons = {R.drawable.icon_1, R.drawable.icon_2,
            R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5};
    String[] iconNames = {"settings", "add occupation", "occupation", "statistics", "color"};

    AddOccupationMVP.PInterface presenter;

    int icon;
    int currentColor = 0xffffffff;

    public static AddOccupationFragment newInstance() {
        Bundle args = new Bundle();
        AddOccupationFragment fragment = new AddOccupationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_occupation, container, false);
        ButterKnife.bind(this, view);
        presenter = new AddOccupationPresenter(this);
        color_chooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();

            }
        });

        icon_spinner.setOnItemSelectedListener(this);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getContext(),icons, iconNames);
        icon_spinner.setAdapter(spinnerAdapter);

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_edit.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(), "Please, fill name", Toast.LENGTH_SHORT).show();
                } else if(color_chooser.getText().toString().equals("Choose your color...")){
                    Toast.makeText(getContext(), "Please, choose color", Toast.LENGTH_SHORT).show();
                }
                else {
                    animateButton(fabDone);
                    String name = name_edit.getText().toString();
                    presenter.addNewOccupation(new OccupationsModel(-1, name, String.format("#%06X", (0xFFFFFF & currentColor)), icon,
                            usefulness_seekbar.getProgress(), pleasure_seekbar.getProgress(), fatigue_seekbar.getProgress()), getContext());

                }
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


    private void createDialog(){
        ColorPickerDialogBuilder
                .with(getContext())
                .setTitle("Choose color")
                .initialColor(currentColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        currentColor = selectedColor;
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        currentColor = selectedColor;
                        color_chooser.setBackgroundColor(currentColor);
                        color_chooser.setText("");
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        LinearLayout linearLayout = (LinearLayout)view;
        ImageView icon = (ImageView)linearLayout.findViewById(R.id.image_spinner_item);
        this.icon = icons[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
