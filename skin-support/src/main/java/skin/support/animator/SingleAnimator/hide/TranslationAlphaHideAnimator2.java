package skin.support.animator.SingleAnimator.hide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.animation.LinearInterpolator;

import skin.support.animator.Action;
import skin.support.animator.SingleAnimator.ViewAnimatorImpl;
import skin.support.animator.SkinAnimator;

/**
 * Created by erfli on 2/25/17.
 */

public class TranslationAlphaHideAnimator2 extends ViewAnimatorImpl {

    private ObjectAnimator animator;

    private TranslationAlphaHideAnimator2() {
    }


    public static TranslationAlphaHideAnimator2 getInstance() {
        return new TranslationAlphaHideAnimator2();
    }

    @Override
    public SkinAnimator apply(@NonNull final View view, @Nullable final Action action) {
        animator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("alpha", 1, 0),
                PropertyValuesHolder.ofFloat("translationY", -view.getHeight()),
                PropertyValuesHolder.ofFloat("translationX", view.getWidth()),
                PropertyValuesHolder.ofFloat("rotation", 270),
                PropertyValuesHolder.ofFloat("scaleX", 0),
                PropertyValuesHolder.ofFloat("scaleY", 0)
        );
        animator.setDuration(5 * PRE_DURATION);
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                resetView(view);
                if (action != null) {
                    action.action();
                }
            }
        });
        return this;
    }

    @Override
    public void start() {
        animator.start();
    }
}
