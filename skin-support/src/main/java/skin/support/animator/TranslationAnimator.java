package skin.support.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by erfli on 2/25/17.
 */

public class TranslationAnimator implements SkinAnimator {
    protected ObjectAnimator preAnimator;
    protected ObjectAnimator afterAnimator;
    protected View targetView;

    private TranslationAnimator() {
    }

    public static TranslationAnimator getInstance() {
        TranslationAnimator skinAlphaAnimator = new TranslationAnimator();
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
                        view.getRight(), view.getLeft()))
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
