package book.cook.view.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import book.cook.R;
import book.cook.app.App;
import book.cook.base.BaseActivity;
import book.cook.view.widget.FoldingLayout;
import book.cook.view.widget.OnFoldListener;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.traffic_layout)
    LinearLayout mTrafficLayout;
    @BindView(R.id.life_layout)
    LinearLayout mLifeLayout;
    @BindView(R.id.traffic_bar_layout)
    RelativeLayout mTrafficBarLayout;
    @BindView(R.id.life_bar_layout)
    RelativeLayout mLifeBarLayout;
    @BindView(R.id.traffic_item)
    FoldingLayout mTrafficFoldingLayout;
    @BindView(R.id.life_item)
    FoldingLayout mLifeFoldingLayout;
    @BindView(R.id.bottom_view)
    View mBottomView;
    private final int FOLD_ANIMATION_DURATION = 500;
    private int mNumberOfFolds = 2;
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        //设置子菜单折叠层数
        mTrafficFoldingLayout.setNumberOfFolds(mNumberOfFolds);
        mLifeFoldingLayout.setNumberOfFolds(mNumberOfFolds);
        //将打开的子菜单进行关闭
        animateFold(mTrafficFoldingLayout, FOLD_ANIMATION_DURATION);
        animateFold(mLifeFoldingLayout, FOLD_ANIMATION_DURATION);
        setMarginToTop(1, mLifeLayout);
        setMarginToTop(1, mBottomView);

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mTrafficBarLayout.setOnClickListener(this);
        mLifeBarLayout.setOnClickListener(this);

    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
    }

    private void handleAnimation(final View bar, final FoldingLayout foldingLayout, final View nextParent) {


        foldingLayout.setFoldListener(new OnFoldListener() {

            @Override
            public void onStartFold(float foldFactor) {

//                bar.setClickable(true);
                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onFoldingState(float foldFactor, float foldDrawHeight) {
//                bar.setClickable(false);
                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onEndFold(float foldFactor) {
//                bar.setClickable(true);
                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }
        });

//      foldingLayout.setNumberOfFolds(mNumberOfFolds);
        animateFold(foldingLayout, FOLD_ANIMATION_DURATION);

    }

    /**
     * 展开动画
     *
     * @param foldLayout
     * @param duration
     */
    @SuppressLint("NewApi")
    public void animateFold(FoldingLayout foldLayout, int duration) {
        float foldFactor = foldLayout.getFoldFactor();
        ObjectAnimator animator= ObjectAnimator.ofFloat(foldLayout, "foldFactor", foldFactor, foldFactor > 0 ? 0 : 1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(0);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    private void setMarginToTop(float foldFactor, View nextParent) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) nextParent.getLayoutParams();
        lp.topMargin = (int) (-dp2px(MainActivity.this, 100) * foldFactor) + dp2px(MainActivity.this, 10);
        nextParent.setLayoutParams(lp);
    }

    public final static int dp2px(Context context, float dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    private void resetMarginToTop(View view, float foldFactor, View nextParent) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) nextParent.getLayoutParams();
        lp.topMargin = (int) (-view.getMeasuredHeight() * foldFactor) + dp2px(MainActivity.this, 10);
        nextParent.setLayoutParams(lp);
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
////        //将打开的子菜单进行关闭
////        animateFold(mTrafficFoldingLayout, FOLD_ANIMATION_DURATION);
////        animateFold(mLifeFoldingLayout, FOLD_ANIMATION_DURATION);
////        setMarginToTop(1, mLifeLayout);
////        setMarginToTop(1, mBottomView);
//
//        mLifeBarLayout.setClickable(true);
//        mTrafficBarLayout.setClickable(true);
//        Toast.makeText(App.mContext,"mLifeBarLayout  :"+ mLifeBarLayout.isClickable(),Toast.LENGTH_SHORT).show();
//        Toast.makeText(App.mContext,"mTrafficBarLayout  :"+ mTrafficBarLayout.isClickable(),Toast.LENGTH_SHORT).show();
////        initEvent();
//        mTrafficBarLayout.setOnClickListener(this);
//        mLifeBarLayout.setOnClickListener(this);
//    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.life_bar_layout:
                Toast.makeText(App.mContext,"点击了",Toast.LENGTH_SHORT).show();
                handleAnimation(v, mLifeFoldingLayout, mBottomView);
                break;
            case R.id.traffic_bar_layout:
                Toast.makeText(App.mContext,"点击了",Toast.LENGTH_SHORT).show();
                handleAnimation(v, mTrafficFoldingLayout, mLifeLayout);
                break;
        }
    }
}
