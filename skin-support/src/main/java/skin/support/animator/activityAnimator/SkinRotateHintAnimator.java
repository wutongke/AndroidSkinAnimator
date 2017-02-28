package skin.support.animator.activityAnimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import skin.support.animator.Action;
import skin.support.animator.SkinAnimator;

/**
 * Created by erfli on 2/25/17.
 */

public class SkinRotateHintAnimator implements SkinAnimator {
    protected ObjectAnimator preAnimator;
    protected ObjectAnimator middleAnimator;
    protected ObjectAnimator afterAnimator;
    protected View targetView;

    private SkinRotateHintAnimator() {
    }

    public static SkinRotateHintAnimator getInstance() {
        SkinRotateHintAnimator skinAlphaAnimator = new SkinRotateHintAnimator();
        return skinAlphaAnimator;
    }

    @Override
    public SkinAnimator apply(@NonNull View view, @Nullable final Action action) {
        this.targetView = view;

        int middle = 30;
        int range = 15;
        targetView.setPivotX(targetView.getLeft());
        targetView.setPivotY(targetView.getTop());

        preAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("rotation",
                        0, middle - range, middle + range, middle - range, middle + range, middle - range, middle + range)
        ).setDuration(PRE_DURATION * 8);
        preAnimator.setInterpolator(new LinearInterpolator());
        preAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                middleAnimator.start();
            }
        });

        middleAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("TranslationY", targetView.getTop(), targetView.getBottom()),
                PropertyValuesHolder.ofFloat("alpha", 1, 0)
        ).setDuration(PRE_DURATION * 2);
        middleAnimator.setInterpolator(new AccelerateInterpolator());

        middleAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (action != null) {
                    action.action();
                }
                afterAnimator.start();
            }
        });

        afterAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("rotation",
                        0, 0),
                PropertyValuesHolder.ofFloat("alpha", 1, 1),
//                PropertyValuesHolder.ofFloat("translationX",
//                        targetView.getLeft() - targetView.getWidth(), targetView.getLeft()),
                PropertyValuesHolder.ofFloat("translationY",
                        targetView.getTop() - targetView.getHeight(), targetView.getTop())
        ).setDuration(AFTER_DURATION * 2);
        afterAnimator.setInterpolator(new LinearInterpolator());

        afterAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                targetView.setPivotX(targetView.getWidth() / 2);
                targetView.setPivotY(targetView.getHeight() / 2);
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
