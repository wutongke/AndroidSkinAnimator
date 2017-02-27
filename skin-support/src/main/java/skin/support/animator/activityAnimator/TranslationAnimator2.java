package skin.support.animator.activityAnimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import skin.support.animator.Action;
import skin.support.animator.SkinAnimator;

/**
 * Created by erfli on 2/25/17.
 */

public class TranslationAnimator2 implements SkinAnimator {
    protected ObjectAnimator preAnimator;
    protected ObjectAnimator afterAnimator;
    protected View targetView;

    private TranslationAnimator2() {
    }

    public static TranslationAnimator2 getInstance() {
        TranslationAnimator2 skinAlphaAnimator = new TranslationAnimator2();
        return skinAlphaAnimator;
    }

    @Override
    public SkinAnimator apply(@NonNull View view, @Nullable final Action action) {
        this.targetView = view;
        preAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("translationX",
                        view.getLeft(), view.getRight()))
                .setDuration(PRE_DURATION * 3);
        preAnimator.setInterpolator(new AccelerateInterpolator());
        afterAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("translationX",
                        view.getLeft() - view.getWidth(), view.getLeft()))
                .setDuration(AFTER_DURATION * 3);
        afterAnimator.setInterpolator(new BounceInterpolator());

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
