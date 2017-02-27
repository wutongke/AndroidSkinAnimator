package skin.support.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import skin.support.SkinCompatManager;
import skin.support.animator.Action;
import skin.support.animator.SingleAnimator.AnimatorConfig;
import skin.support.animator.SingleAnimator.AnimatorManager;
import skin.support.animator.activityAnimator.SkinActivityAnimator;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;

/**
 * Created by ximsfei on 17-1-8.
 */

public class SkinCompatActivity extends AppCompatActivity implements SkinObserver {

    private SkinCompatDelegate mSkinDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), getSkinDelegate());
        super.onCreate(savedInstanceState);
    }

    @NonNull
    public SkinCompatDelegate getSkinDelegate() {
        if (mSkinDelegate == null) {
            mSkinDelegate = SkinCompatDelegate.create(this);
        }
        return mSkinDelegate;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinCompatManager.getInstance().addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinCompatManager.getInstance().deleteObserver(this);
    }

    @Override
    public void updateSkin(SkinObservable observable, Object o) {
        View rootView = findViewById(android.R.id.content);
        SkinActivityAnimator.updateSkin(rootView, new Action() {
            @Override
            public void action() {
                getSkinDelegate().applySkin();
            }
        });
    }

    protected void setAnimatorConfig(AnimatorConfig animatorConfig){
        AnimatorManager.setConfig(animatorConfig);
    }
}
