package skin.support.animator.SingleAnimator;

import android.view.View;

import skin.support.animator.Action;
import skin.support.animator.SingleAnimator.update.AlphaAnimator;

/**
 * Created by erfli on 2/28/17.
 */

public enum ViewAnimatorType {
    //Visible
    //Update

    AlphaUpdateAnimator() {
        @Override
        public void apply(View view, Action action) {
            AlphaAnimator.getInstance().apply(view, action).start();
        }
    },

    //Hide
    AlphaHideAnimator() {
        @Override
        public void apply(View view, Action action) {
            skin.support.animator.SingleAnimator.hide.AlphaHideAnimator.getInstance().apply(view, action).start();
        }
    },
    RotationHideAnimator() {
        @Override
        public void apply(View view, Action action) {
            skin.support.animator.SingleAnimator.hide.RotationHideAnimator.getInstance().apply(view, action).start();
        }
    },
    ScaleHideAnimator() {
        @Override
        public void apply(View view, Action action) {
            skin.support.animator.SingleAnimator.hide.ScaleHideAnimator.getInstance().apply(view, action).start();
        }
    },
    TranslationAlphaHideAnimator() {
        @Override
        public void apply(View view, Action action) {
            skin.support.animator.SingleAnimator.hide.TranslationAlphaHideAnimator.getInstance().apply(view, action).start();
        }
    },
    TranslationAlphaHideAnimator2() {
        @Override
        public void apply(View view, Action action) {
            skin.support.animator.SingleAnimator.hide.TranslationAlphaHideAnimator2.getInstance().apply(view, action).start();
        }
    },
    TranslationHideAnimator() {
        @Override
        public void apply(View view, Action action) {
            skin.support.animator.SingleAnimator.hide.TranslationHideAnimator.getInstance().apply(view, action).start();
        }
    },
    TranslationHideAnimator2() {
        @Override
        public void apply(View view, Action action) {
            skin.support.animator.SingleAnimator.hide.TranslationHideAnimator2.getInstance().apply(view, action).start();
        }
    },
    TranslationRotationHideAnimator2() {
        @Override
        public void apply(View view, Action action) {
            skin.support.animator.SingleAnimator.hide.TranslationRotationHideAnimator2.getInstance().apply(view, action).start();
        }
    },

    None() {
        @Override
        public void apply(View view, Action action) {

        }
    };


    public abstract void apply(View view, Action action);
}
