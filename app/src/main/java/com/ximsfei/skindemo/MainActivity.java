package com.ximsfei.skindemo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.ximsfei.skindemo.databinding.ActivityMainBinding;
import com.ximsfei.skindemo.databinding.MainHeaderLayoutBinding;
import com.ximsfei.skindemo.ui.AboutSkinLibActivity;
import com.ximsfei.skindemo.ui.DiscoverFragment;
import com.ximsfei.skindemo.ui.FriendsFragment;
import com.ximsfei.skindemo.ui.MusicFragment;
import com.ximsfei.skindemo.ui.SkinLibActivity;
import com.ximsfei.skindemo.ui.adapter.TabFragmentPagerAdapter;
import com.ximsfei.skindemo.ui.base.BaseActivity;
import com.ximsfei.skindemo.utils.SPUtils;
import com.ximsfei.skindemo.widget.ArrayDialogFragment;

import java.util.ArrayList;
import java.util.List;

import skin.support.SkinCompatManager;
import skin.support.animator.Action;
import skin.support.animator.AnimatorType;
import skin.support.animator.SingleAnimator.AnimatorConfig;
import skin.support.animator.SingleAnimator.ViewAnimatorType;
import skin.support.animator.activityAnimator.SkinActivityAnimator;

import static com.ximsfei.skindemo.DataManager.NIGHT_SKIN;
import static com.ximsfei.skindemo.DataManager.SKIN_LIBS;
import static com.ximsfei.skindemo.DataManager.SKIN_NAMES;

/**
 * Created by ximsfei on 17-1-7.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private final static String TAG = MainActivity.class.getSimpleName();
    private static final int SELECT_SKIN_REQUEST_CODE = 100;

    private ViewPagerListener mViewPagerListener = new ViewPagerListener();
    private int mCurrentFragment = TabState.DEFAULT;

    private MainHeaderLayoutBinding mMainHeaderBinding;
    private ArrayDialogFragment dialogFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding.setListener(this);
        initToolbar(mDataBinding.toolBar);
        initNavigationView(mDataBinding.navigationView);
        configAnimator();
        configFragments();
        initConfigAnimatorDialog();
    }

    @Override
    protected boolean needAnimator() {
        return true;
    }

    private void initConfigAnimatorDialog() {
        List<String> itemList = new ArrayList<>();
        for (AnimatorType animatorType : AnimatorType.values()) {
            itemList.add(animatorType.name());
        }
        dialogFragment = new ArrayDialogFragment();
        dialogFragment.setItemList(itemList);
        dialogFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SkinActivityAnimator.configActivityAnimatorType(AnimatorType.values()[position]);
                changeNightMode();
            }
        });
    }

    private void configAnimator() {
        setAnimatorConfig(new AnimatorConfig
                .Builder()
                .textviewTextAnimationType(ViewAnimatorType.AlphaUpdateAnimator)
                .textviewVisibleAnimationType(ViewAnimatorType.TranslationAlphaHideAnimator)
                .build());
    }

    @Override
    protected void initToolbar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBar.setNavigationIcon(R.drawable.ic_menu);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataBinding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initNavigationView(NavigationView navigationView) {
        View headerView = getLayoutInflater().inflate(R.layout.main_header_layout, null, false);
        navigationView.addHeaderView(headerView);
        mMainHeaderBinding = DataBindingUtil.bind(headerView);
        mMainHeaderBinding.setListener(this);
        initSkinName();
    }

    public void goAboutSkinLibActivity(View view) {
        Intent intent = new Intent(this, AboutSkinLibActivity.class);
        startActivity(intent);
    }

    public void goSkinLibActivity(View view) {
        mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
        Intent intent = new Intent(this, SkinLibActivity.class);
        startActivityForResult(intent, SELECT_SKIN_REQUEST_CODE);
    }

    public boolean getNightMode() {
        return SPUtils.getInstance().getNightMode();
    }

    public void onNightModeClick(View view) {
        changeNightMode();
    }

    private void changeNightMode() {
        if (!SPUtils.getInstance().getNightMode()) {
            SPUtils.getInstance().setCurSkin(SkinCompatManager.getInstance().getCurSkinName()).commitEditor();
            SkinCompatManager.getInstance().loadSkin(NIGHT_SKIN);
        } else {
            SkinCompatManager.getInstance().loadSkin(SPUtils.getInstance().getCurSkin());
        }
        SPUtils.getInstance().setNightMode(!SPUtils.getInstance().getNightMode()).commitEditor();
        mMainHeaderBinding.dayNightSwitch.setChecked(SPUtils.getInstance().getNightMode());
    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new DiscoverFragment());
        list.add(new MusicFragment());
        list.add(new FriendsFragment());
        mDataBinding.viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), list));
        mDataBinding.viewPager.addOnPageChangeListener(mViewPagerListener);
        setPageSelected(mCurrentFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_choose:
                dialogFragment.show(getSupportFragmentManager(), "");
                break;
            case R.id.menu_change:
                changeNightMode();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goDiscoverTab(View view) {
        setPageSelected(TabState.DEFAULT);
    }

    public void goMusicTab(View view) {
        setPageSelected(TabState.MUSIC);
    }

    public void goFriendsTab(View view) {
        setPageSelected(TabState.FRIENDS);
    }

    public void setPageSelected(int position) {
        mCurrentFragment = position;
        if (position != mDataBinding.viewPager.getCurrentItem()) {
            mDataBinding.viewPager.setCurrentItem(position);
        }
        mDataBinding.discover.setSelected(false);
        mDataBinding.music.setSelected(false);
        mDataBinding.friends.setSelected(false);
        switch (position) {
            case TabState.DEFAULT:
                mDataBinding.discover.setSelected(true);
                break;
            case TabState.MUSIC:
                mDataBinding.music.setSelected(true);
                break;
            case TabState.FRIENDS:
                mDataBinding.friends.setSelected(true);
                break;
        }
    }

    public interface TabState {
        int DEFAULT = 0;
        int MUSIC = 1;
        int FRIENDS = 2;
    }

    public class ViewPagerListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            setPageSelected(position);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (SELECT_SKIN_REQUEST_CODE == requestCode) {
            initSkinName();
        }
    }

    private void initSkinName() {
        String curSkin = SkinCompatManager.getInstance().getCurSkinName();
        String curSkinName = SKIN_NAMES[0];
        for (int i = 0; i < SKIN_LIBS.length; i++) {
            if (SKIN_LIBS[i].equals(curSkin)) {
                curSkinName = SKIN_NAMES[i];
                break;
            }
        }
        mMainHeaderBinding.curSkinName.setText(curSkinName);
        mMainHeaderBinding.dayNightSwitch.setChecked(SPUtils.getInstance().getNightMode());
    }

    @Override
    public void onBackPressed() {
        if (mDataBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }
}
