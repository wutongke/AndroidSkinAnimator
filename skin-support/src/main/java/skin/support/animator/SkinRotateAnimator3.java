package skin.support.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by erfli on 2/25/17.
 */

public class SkinRotateAnimator3 implements SkinAnimator {
    protected ObjectAnimator preAnimator;
    protected View targetView;

    private SkinRotateAnimator3() {
    }

    public static SkinRotateAnimator3 getInstance() {
        SkinRotateAnimator3 skinAlphaAnimator = new SkinRotateAnimator3();
        return skinAlphaAnimator;
    }

    @Override
    public SkinAnimator apply(@NonNull View view, @Nullable final Action action) {
        this.targetView = view;
        preAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView,
                PropertyValuesHolder.ofFloat("scaleX",
                        1, 0.5f, 0.2f, 0.05f, 0.8f, 1),
                PropertyValuesHolder.ofFloat("scaleY",
                        1, 0.5f, 0.2f, 0.05f, 0.8f, 1),
                PropertyValuesHolder.ofFloat("rotationY", 0, 720))
                .setDuration(PRE_DURATION * 3);
        preAnimator.setInterpolator(new LinearInterpolator());

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(action != null){
                    action.action();
                }
            }
        }, PRE_DURATION);

        preAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
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
