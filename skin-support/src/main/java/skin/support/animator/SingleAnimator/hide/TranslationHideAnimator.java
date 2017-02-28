package skin.support.animator.SingleAnimator.hide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import skin.support.animator.Action;
import skin.support.animator.SingleAnimator.ViewAnimatorImpl;
import skin.support.animator.SkinAnimator;

/**
 * Created by erfli on 2/25/17.
 */

public class TranslationHideAnimator extends ViewAnimatorImpl {

    private ObjectAnimator animator;

    private TranslationHideAnimator() {
    }


    public static TranslationHideAnimator getInstance() {
        return new TranslationHideAnimator();
    }

    @Override
    public SkinAnimator apply(@NonNull final View view, @Nullable final Action action) {
        animator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("alpha", 1, 0),
                PropertyValuesHolder.ofFloat("translationX", view.getLeft(), view.getRight()));
        animator.setDuration(3 * PRE_DURATION);
        animator.setInterpolator(new AnticipateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
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
