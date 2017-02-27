package skin.support.animator.activityAnimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;

import skin.support.animator.Action;
import skin.support.animator.SkinAnimator;

/**
 * Created by erfli on 2/25/17.
 */

public class SkinRotateAnimator2 implements SkinAnimator {
    protected ObjectAnimator preAnimator;
    protected ObjectAnimator afterAnimator;
    protected View targetView;

    private SkinRotateAnimator2() {
    }

    public static SkinRotateAnimator2 getInstance() {
        SkinRotateAnimator2 skinAlphaAnimator = new SkinRotateAnimator2();
        return skinAlphaAnimator;
    }

    @Override
    public SkinAnimator apply(@NonNull View view, @Nullable final Action action) {
        this.targetView = view;
        preAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("rotationY", 0, 90),
                PropertyValuesHolder.ofFloat("scaleX",
                        1, 0.3f),
                PropertyValuesHolder.ofFloat("scaleY",
                        1, 0.5f))
                .setDuration(PRE_DURATION * 3);
        preAnimator.setInterpolator(new LinearInterpolator());
        afterAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("rotationY", 90, 0),
                PropertyValuesHolder.ofFloat("scaleX",
                        0.3f, 1),
                PropertyValuesHolder.ofFloat("scaleY",
                        0.3f, 1))
                .setDuration(AFTER_DURATION * 3);
        afterAnimator.setInterpolator(new LinearInterpolator());

        preAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (action != null) {
                    action.action();
                }
                afterAnimator.start();
            }
        });
        return this;
    }

    @Override
    public SkinAnimator setPreDuration() {
        return this;
    }

    @Override
    public SkinAnimator setAfterDuration() {
        return this;
    }

    @Override
    public SkinAnimator setDuration() {
        return this;
    }

    @Override
    public void start() {
        preAnimator.start();
    }
}
