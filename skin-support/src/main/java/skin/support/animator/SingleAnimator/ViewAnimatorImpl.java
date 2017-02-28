package skin.support.animator.SingleAnimator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import skin.support.animator.Action;
import skin.support.animator.SkinAnimator;

/**
 * Created by erfli on 2/25/17.
 */

public abstract class ViewAnimatorImpl implements SkinAnimator {
    @Override
    public abstract SkinAnimator apply(@NonNull View view, @Nullable Action action);

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
    public abstract void start();

    protected void resetView(View view) {
        view.setAlpha(1);
        view.setScaleY(1);
        view.setScaleX(1);
        view.setRotation(0);
        view.setTranslationX(0);
        view.setTranslationY(0);
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);
    }
}
