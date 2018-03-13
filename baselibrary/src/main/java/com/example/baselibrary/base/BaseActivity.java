package com.example.baselibrary.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.baselibrary.R;
import com.example.baselibrary.lisenter.INavTabLisenter;

public class BaseActivity extends AppCompatActivity{
    protected Context mContext;

    protected RelativeLayout mHeadLayout;
    protected Button mBtnLeft;
    protected Button mBtnRight;
    protected TextView mTitle;
    protected TextView mHeadRightText;
    private Drawable mBtnBackDrawable;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.base_activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
        mContext = this;
    }

    @Override
    public void setContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        // 初始化公共头部
        mHeadLayout = (RelativeLayout) view.findViewById(R.id.layout_base_head);
        mHeadRightText = (TextView) view.findViewById(R.id.text_right);
        mBtnLeft = (Button) view.findViewById(R.id.btn_left);
        mBtnRight = (Button) view.findViewById(R.id.btn_right);
        mTitle = (TextView) view.findViewById(R.id.tv_title);
        mBtnBackDrawable = getResources().getDrawable(R.drawable.header_back_icon);
        mBtnBackDrawable.setBounds(0, 0, mBtnBackDrawable.getMinimumWidth(),
                mBtnBackDrawable.getMinimumHeight());
        addContentView(view,lp);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
        setLoadView();
    }

    private void setLoadView(){
        // 获取Dialog布局
        if(dialog == null){
            dialog = new Dialog(this,R.style.AlertDialogStyle);
            dialog.setCanceledOnTouchOutside(false);
            View view = LayoutInflater.from(this).inflate(
                    R.layout.view_loading_alertdialog, null);
            dialog.setContentView(view);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    //拦截返回键的操作，点击返回键返回false会消失当前窗口，返回true待鉴定
//                    HttpManger.getHttpInstance().cancelAllRequest();
                    return false;
                }
            });
        }
    }

    /**
     * 显示加载框
     */
    public void show(){
        if(dialog != null && !dialog.isShowing()){
            dialog.show();
        }
    }

    /**
     * 隐藏加载框
     */
    public void dismiss(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    /**
     * 初始化数据
     */
    public void initCreateContent(){}

    /**
     * 设置头部是否可见
     *
     * @param visibility
     */
    public void setHeadVisibility(int visibility) {
        mHeadLayout.setVisibility(visibility);
    }

    /**
     * 设置左边是否可见
     *
     * @param visibility
     */
    public void setHeadLeftButtonVisibility(int visibility) {
        mBtnLeft.setVisibility(visibility);
    }

    /**
     * 设置右边是否可见
     *
     * @param visibility
     */
    public void setHeadRightButtonVisibility(int visibility) {
        mBtnRight.setVisibility(visibility);
    }

    /**
     * 设置标题
     */
    public void setTitle(int titleId) {
        setTitle(getString(titleId), false);
    }

    /**
     * 设置标题
     */
    public void setTitle(int titleId, boolean flag) {
        setTitle(getString(titleId), flag);
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        setTitle(title, false);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title, boolean flag) {
        mTitle.setText(title);
        if (flag) {
            mBtnLeft.setCompoundDrawables(null, null, null, null);
        } else {
            mBtnLeft.setCompoundDrawables(mBtnBackDrawable, null, null, null);
        }
    }

    /**
     * 点击左按钮
     */
    public void onHeadLeftButtonClick(View v) {
        finish();
    }

    /**
     * 点击右按钮
     */
    public void onHeadRightButtonClick(View v) {

    }

    public Button getHeadLeftButton() {
        return mBtnLeft;
    }

    public void setHeadLeftButton(Button leftButton) {
        this.mBtnLeft = leftButton;
    }

    public Button getHeadRightButton() {
        return mBtnRight;
    }

    public void setHeadRightButton(Button rightButton) {
        this.mBtnRight = rightButton;
    }

    public Drawable getHeadBackButtonDrawable() {
        return mBtnBackDrawable;
    }

    public void setBackButtonDrawable(Drawable backButtonDrawable) {
        this.mBtnBackDrawable = backButtonDrawable;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus() && event.getAction() == MotionEvent.ACTION_UP) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

}
