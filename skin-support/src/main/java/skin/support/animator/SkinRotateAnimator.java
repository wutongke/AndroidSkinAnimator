package skin.support.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by erfli on 2/25/17.
 */

public class SkinRotateAnimator implements SkinAnimator {
    protected ObjectAnimator preAnimator;
    protected ObjectAnimator afterAnimator;
    protected View targetView;

    private SkinRotateAnimator() {
    }

    public static SkinRotateAnimator getInstance() {
        SkinRotateAnimator skinAlphaAnimator = new SkinRotateAnimator();
        return skinAlphaAnimator;
    }

    @Override
    public SkinAnimator apply(@NonNull View view, @Nullable final Action action) {
        this.targetView = view;
        preAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("translationX",
                        view.getLeft(), view.getLeft() - view.getWidth()),
                PropertyValuesHolder.ofFloat("scaleX",
                        0.3f),
                PropertyValuesHolder.ofFloat("scaleY",
                        0.3f),
                PropertyValuesHolder.ofFloat("rotationY", 0, -90))
                .setDuration(PRE_DURATION * 3);
        preAnimator.setInterpolator(new LinearInterpolator());
        afterAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("translationX",
                        view.getLeft() - view.getWidth(), view.getLeft()),
                PropertyValuesHolder.ofFloat("scaleX",
                        1),
                PropertyValuesHolder.ofFloat("scaleY",
                        1),
                PropertyValuesHolder.ofFloat("rotationY", -90, 0))
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
