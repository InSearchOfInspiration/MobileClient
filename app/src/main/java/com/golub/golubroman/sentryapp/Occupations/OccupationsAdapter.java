package com.golub.golubroman.sentryapp.Occupations;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golub.golubroman.sentry.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OccupationsAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
                                {

    private List<OccupationsModel> occupationsList;
    CustomItemClickListener customItemClickListener;

    public OccupationsAdapter(List<OccupationsModel> occupationsList, RecyclerView recyclerView,
                              CustomItemClickListener customItemClickListener){
        this.occupationsList = occupationsList;
        this.customItemClickListener = customItemClickListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
            final View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_occupation, parent, false);
        vh = new OccupationsAdapter.OccupationsViewHolder(view, parent.getContext());
        final RecyclerView.ViewHolder finalVh = vh;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemClickListener.onItemClick(v, ((OccupationsViewHolder)finalVh), sendAdapter());
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                customItemClickListener.onLongItemClick(v, ((OccupationsViewHolder)finalVh), sendAdapter());
                ((OccupationsViewHolder)finalVh).setItemRecyclerViewAnimation(v);
                return true;
            }
        });
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OccupationsViewHolder)holder).nameTextView.
                setText(occupationsList.get(position).getOccupationsName());
        ((OccupationsViewHolder)holder).iconImageView.
                setImageDrawable(((OccupationsViewHolder) holder).context.
                        getResources().getDrawable(occupationsList.get(position).getOccupationsIcon()));
        ((OccupationsViewHolder)holder).backgroundCardView.
                setBackgroundColor(Color.parseColor(occupationsList.get(position).getOccupationsColor()));
    }

    public OccupationsAdapter<T> sendAdapter(){
        return this;
    }

    @Override
    public int getItemCount() {
        return occupationsList.size();
    }


    public class OccupationsViewHolder extends RecyclerView.ViewHolder
            implements OccupationsFragment.OnDismissPopupMenuListener{

        Context context;
        View view;
        int originalHeight = 0;
        boolean mIsViewExpanded = false;
        @BindView(R.id.name_item_occupation)
        TextView nameTextView;
        @BindView(R.id.icon_item_occupation)
        ImageView iconImageView;
        @BindView(R.id.background_item_occupation)
        CardView backgroundCardView;
        @BindView(R.id.extended_item_occupation)
        RelativeLayout yourCustomView;

        public OccupationsViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
            this.view = itemView;
            if (mIsViewExpanded == false) {
                yourCustomView.setVisibility(View.GONE);
                yourCustomView.setEnabled(false);
            }
        }
        public void setItemRecyclerViewAnimation(final View view){
            if (originalHeight == 0) {
                originalHeight = view.getHeight();
            }
            ValueAnimator valueAnimator;
            if (!mIsViewExpanded) {
                alphaAnimPlus(yourCustomView);
                alphaAnimMinus(nameTextView);
                alphaAnimMinus(iconImageView);
                mIsViewExpanded = true;
                valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + 40);
            } else {

                mIsViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(originalHeight + 40, originalHeight);
                alphaAnimMinus(yourCustomView);
                alphaAnimPlus(nameTextView);
                alphaAnimPlus(iconImageView);
            }
            valueAnimator.setDuration(200);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();
                    view.getLayoutParams().height = value.intValue();
                    view.requestLayout();
                }
            });


            valueAnimator.start();
        }
        private void alphaAnimPlus(final View view){
            Animation a = new AlphaAnimation(0.00f, 1.00f); // Fade out

            a.setDuration(200);
            // Set a listener to the animation and configure onAnimationEnd
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            // Set the animation on the custom view
            view.startAnimation(a);
        }
        private void alphaAnimMinus(final View view){
            Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

            a.setDuration(200);
            // Set a listener to the animation and configure onAnimationEnd
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            // Set the animation on the custom view
            view.startAnimation(a);
        }

        @Override
        public void onDismissPopupMenu() {
            setItemRecyclerViewAnimation(view);
        }
    }

    public List<OccupationsModel> getOccupationsList(){
        return occupationsList;
    }
}
