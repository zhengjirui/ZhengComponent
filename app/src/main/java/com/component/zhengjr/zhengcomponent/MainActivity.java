package com.component.zhengjr.zhengcomponent;

import android.os.Bundle;
import android.view.View;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.base.BaseNavTabActivity;

public class MainActivity extends BaseNavTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.base_activity_main);
//        setHeadVisibility(View.GONE);
        setHeadLeftButtonVisibility(View.GONE);
        setTitle("首页");
        initCreateContent();
    }


    @Override
    public void initCreateContent() {
        super.initCreateContent();
        this.setCurrentTab(1);
    }

    @Override
    public void INavCurrentLisenter(int navCurrent) {
        super.INavCurrentLisenter(navCurrent);
        switch (navCurrent) {
            case 0:
                setTitle("首页");
                break;

            case 1:
                setTitle("商铺");
                break;

            case 2:
                setTitle("用户 ");
                break;
            default:
                setTitle("");
                break;

        }


    }
}
