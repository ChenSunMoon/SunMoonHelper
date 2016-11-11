package sunmoon.com.helper;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import sunmoon.com.helper.activity.BaseActivity;
import sunmoon.com.helper.activity.VoiceActivity;
import sunmoon.com.helper.adapter.MainAdapter;
import sunmoon.com.helper.databinding.ActivityMainBinding;
import sunmoon.com.helper.fragment.MessageFragment;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private ActivityMainBinding b;
    private MenuItem mCurrentItem;
    private MainAdapter mainAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initData();
        initView();

    }

    private void initData() {
        List<Fragment> mFragments=new ArrayList<>();
        mFragments.add(MessageFragment.newInstance());
        mainAdapter=new MainAdapter(getSupportFragmentManager(),mFragments);
    }

    private void initView() {
        //底部导航
        mCurrentItem=b.bnvBottom.getMenu().getItem(0);
        b.bnvBottom.setOnNavigationItemSelectedListener(this);

        //ViewPager
        b.vpMain.setAdapter(mainAdapter);




    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (mCurrentItem!=item){
            mCurrentItem=item;
            if (item.getTitle().equals("常用")){
                Intent intent=new Intent(MainActivity.this, VoiceActivity.class);
                MainActivity.this.startActivity(intent);
            }
            return true;
        }
        return false;
    }
}
