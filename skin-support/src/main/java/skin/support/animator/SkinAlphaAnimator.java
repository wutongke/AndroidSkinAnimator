package skin.support.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by erfli on 2/25/17.
 */

public class SkinAlphaAnimator implements SkinAnimator {
    protected ObjectAnimator preAnimator;
    protected ObjectAnimator afterAnimator;
    protected View targetView;

    private SkinAlphaAnimator() {
    }

    public static SkinAlphaAnimator getInstance() {
        SkinAlphaAnimator skinAlphaAnimator = new SkinAlphaAnimator();
        return skinAlphaAnimator;
    }

    @Override
    public SkinAnimator apply(@NonNull View view, @Nullable final Action action) {
        this.targetView = view;
        preAnimator = ObjectAnimator.ofFloat(targetView, "alpha", 1, 0)
                .setDuration(PRE_DURATION);
        preAnimator.setInterpolator(new LinearInterpolator());
        afterAnimator = ObjectAnimator.ofFloat(targetView, "alpha", 0, 1)
                .setDuration(AFTER_DURATION);
        afterAnimator.setInterpolator(new LinearInterpolator());

        preAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(action != null){
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
