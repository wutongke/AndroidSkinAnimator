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

public class ScaleAnimator implements SkinAnimator {
    protected ObjectAnimator preAnimator;
    protected ObjectAnimator afterAnimator;
    protected View targetView;

    private ScaleAnimator() {
    }

    public static ScaleAnimator getInstance() {
        ScaleAnimator skinAlphaAnimator = new ScaleAnimator();
        return skinAlphaAnimator;
    }

    @Override
    public SkinAnimator apply(@NonNull View view, @Nullable final Action action) {
        this.targetView = view;
        targetView.setPivotX(targetView.getLeft());
        targetView.setPivotY(targetView.getTop());
        preAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("ScaleX",
                        1, 0),
                PropertyValuesHolder.ofFloat("ScaleY",
                        1, 0))
                .setDuration(PRE_DURATION * 3);
        preAnimator.setInterpolator(new LinearInterpolator());
        afterAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("ScaleX",
                        0, 1),
                PropertyValuesHolder.ofFloat("ScaleY",
                        0, 1))
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

        afterAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                targetView.setPivotX((targetView.getLeft() + targetView.getRight()) / 2);
                targetView.setPivotY((targetView.getTop() + targetView.getBottom()) / 2);
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
