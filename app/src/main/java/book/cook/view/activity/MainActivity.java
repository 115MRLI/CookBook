package book.cook.view.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import book.cook.R;
import book.cook.base.BaseActivity;
import book.cook.view.widget.FoldingLayout;
import book.cook.view.widget.OnFoldListener;

public class MainActivity extends BaseActivity {
    private LinearLayout mTrafficLayout;
    private RelativeLayout mTrafficBarLayout;
    private FoldingLayout mTrafficFoldingLayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mTrafficLayout = (LinearLayout) findViewById(R.id.traffic_layout);
        mTrafficBarLayout = (RelativeLayout) findViewById(R.id.traffic_bar_layout);
        mTrafficFoldingLayout = ((FoldingLayout) findViewById(R.id.traffic_item));

        final View mBottomView = findViewById(R.id.bottom_view);

        mTrafficBarLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                handleAnimation(v, mTrafficFoldingLayout, mTrafficLayout, mBottomView);
            }
        });
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
    @Override
    protected void getIntentData() {
        super.getIntentData();
    }
    private void handleAnimation(final View bar, final FoldingLayout foldingLayout, View parent, final View nextParent) {

        final ImageView arrow = (ImageView) parent.findViewById(R.id.traffic_arrow);

        foldingLayout.setFoldListener(new OnFoldListener() {

            @Override
            public void onStartFold(float foldFactor) {

//                bar.setClickable(true);
//                arrow.setBackgroundResource(R.drawable.service_arrow_up);
//                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onFoldingState(float foldFactor, float foldDrawHeight) {
//                bar.setClickable(false);
//                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onEndFold(float foldFactor) {

//                bar.setClickable(true);
//                arrow.setBackgroundResource(R.drawable.service_arrow_down);
//                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }
        });

        animateFold(foldingLayout, 1000);

    }
    @SuppressLint("NewApi")
    public void animateFold(FoldingLayout foldLayout, int duration) {
        float foldFactor = foldLayout.getFoldFactor();

        ObjectAnimator animator = ObjectAnimator.ofFloat(foldLayout, "foldFactor", foldFactor, foldFactor > 0 ? 0 : 1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(0);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }
}
