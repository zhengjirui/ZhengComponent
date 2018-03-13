package com.example.baselibrary.base;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.baselibrary.R;
import com.example.baselibrary.lisenter.INavTabLisenter;
import com.example.baselibrary.view.NavTabLayout;

import java.util.List;

public class BaseNavTabActivity extends BaseActivity implements INavTabLisenter {
    private final String TAG = "BaseNavTabActivity";

    private FrameLayout mFragment;
    private NavTabLayout mNavTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_nav_tab);
    }

    @Override
    public void initCreateContent() {
        super.initCreateContent();
        mFragment = findViewById(R.id.fragment_layout);
        mNavTabLayout = findViewById(R.id.nav_tab_layout);
        mNavTabLayout.setNavTabLisenter(this);

    }


    @Override
    public void INavCurrentLisenter(int navCurrent) {
        Log.e(TAG,navCurrent + "");
    }

    public void setFragmentList(List<Fragment> fragmentList){

    }

    public void setCurrentTab(int currentTab){
        if(mNavTabLayout != null){
            mNavTabLayout.setTabCurrent(currentTab);
        }
    }
}
