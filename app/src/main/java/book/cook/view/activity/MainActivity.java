package book.cook.view.activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.dxtt.coolmenu.CoolMenuFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import book.cook.R;
import book.cook.base.BaseActivity;
import book.cook.view.fragment.Fragment1;
import book.cook.view.fragment.Fragment2;
import book.cook.view.fragment.Fragment3;
import book.cook.view.fragment.Fragment4;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_main)
    CoolMenuFrameLayout coolMenuFrameLayout;

    List<String> titleList = null;
    List<Fragment> fragments = new ArrayList<>();
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        String[] titles = {"美食精选", "电饭煲食谱", "早餐食谱", "懒人菜谱"};
        titleList = Arrays.asList(titles);
        //set your titles,which is optional
        coolMenuFrameLayout.setTitles(titleList);
        //set your menu icon
        coolMenuFrameLayout.setMenuIcon(R.mipmap.menu);
    }

    @Override
    protected void initEvent() {
        super.initEvent();

    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        coolMenuFrameLayout.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {

    }
}
