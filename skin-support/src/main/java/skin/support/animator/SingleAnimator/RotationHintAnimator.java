package skin.support.animator.SingleAnimator;

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

public class RotationHintAnimator extends SingleAnimatorImpl {

    private ObjectAnimator animator;
    private ObjectAnimator afterAnimator;

    private RotationHintAnimator() {
    }


    public static RotationHintAnimator getInstance() {
        return new RotationHintAnimator();
    }

    @Override
    public SkinAnimator apply(@NonNull final View view, @Nullable final Action action) {
        view.setPivotY(0);
        view.setPivotX(0);
        view.animate().translationX(view.getHeight() * 2).setDuration(0).start();
        animator = ObjectAnimator.ofFloat(view, "rotation", 0, 90, 55, 90, 55, 90, 55, 90);
        animator.setDuration(PRE_DURATION * 8);
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                afterAnimator.start();
            }
        });

        afterAnimator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("translationY", view.getTop(), view.getTop() + view.getHeight() * 3),
                PropertyValuesHolder.ofFloat("alpha", 1, 0)).setDuration(AFTER_DURATION * 3);
        afterAnimator.setInterpolator(new LinearInterpolator());
        afterAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
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
