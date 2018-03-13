package com.example.baselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baselibrary.R;
import com.example.baselibrary.lisenter.INavTabLisenter;
import com.example.baselibrary.utils.Utils;

/**
 * Created by jinding on 2018/3/10.
 */

public class NavTabLayout extends LinearLayout implements View.OnClickListener {
    private final String TAG = "CusLinearLayout";
    private Context mContent;
    private int mTabCurrent;//默认显示的下标为0
    private TextView mTabHome;
    private TextView mTabVideo;
    private TextView mTabMine;
    private INavTabLisenter mINavTabLisenter;

    public NavTabLayout(Context context) {
        this(context,null);
    }

    public NavTabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public NavTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContent = context;
        initView(attrs);
    }

    public void initView(@Nullable AttributeSet attrs){
        TypedArray typedArray = mContent.obtainStyledAttributes(attrs, R.styleable.NavLayout);
        mTabCurrent = typedArray.getInteger(R.styleable.NavLayout_navTabCurrent, 0);
        //添加布局
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.nav_bottom_layout, this,false);
        this.addView(inflate);

        mTabHome = inflate.findViewById(R.id.tab_home_txt);
        mTabVideo = inflate.findViewById(R.id.tab_video_txt);
        mTabMine = inflate.findViewById(R.id.tab_mine_txt);

        setDrawableTop(mTabHome,R.drawable.nav_tab_home_select);
        setDrawableTop(mTabVideo,R.drawable.nav_tab_video_select);
        setDrawableTop(mTabMine,R.drawable.nav_tab_mine_select);

        mTabHome.setOnClickListener(this);
        mTabVideo.setOnClickListener(this);
        mTabMine.setOnClickListener(this);

        setTabCurrent(mTabCurrent);
    }

    private void setDrawableTop(TextView tab,int select){
        Drawable drawable = getResources().getDrawable(select);
        drawable.setBounds(0, 0, Utils.dp2px(mContent, 25), Utils.dp2px(mContent, 25));
        tab.setCompoundDrawables(null, drawable, null, null);
        tab.setCompoundDrawablePadding(Utils.dp2px(mContent,5));//设置图片和文字之间的高度
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        setAllUnSelect();
        if ( id== R.id.tab_home_txt ){
            mTabHome.setSelected(true);
            if(mINavTabLisenter != null){
                mINavTabLisenter.INavCurrentLisenter(0);
            }
        }else if(id== R.id.tab_video_txt){
            mTabVideo.setSelected(true);
            if(mINavTabLisenter != null){
                mINavTabLisenter.INavCurrentLisenter(1);
            }
        }else if(id== R.id.tab_mine_txt){
            mTabMine.setSelected(true);
            if(mINavTabLisenter != null){
                mINavTabLisenter.INavCurrentLisenter(2);
            }
        }
    }

    private void setAllUnSelect(){
        mTabHome.setSelected(false);
        mTabVideo.setSelected(false);
        mTabMine.setSelected(false);
    }

    public void setTabCurrent(int tabCurrent){
        setAllUnSelect();
        if ( tabCurrent== 0 ){
            mTabHome.setSelected(true);
        }else if(tabCurrent== 1){
            mTabVideo.setSelected(true);
        }else if(tabCurrent== 2){
            mTabMine.setSelected(true);
        }
        if(mINavTabLisenter != null ){
            mINavTabLisenter.INavCurrentLisenter(tabCurrent);
        }

    }

    public void setNavTabLisenter(INavTabLisenter iNavTabLisenter){
        this.mINavTabLisenter = iNavTabLisenter;
    }

}
