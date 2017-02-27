package skin.support.animator;

import android.view.View;

/**
 * Created by erfli on 2/26/17.
 */

public enum ActivityAnimatorType {
    ALPHA{
        @Override
        public void apply(View view, Action action) {
            SkinAlphaAnimator.getInstance().apply(view, action).start();
        }
    },
    ROTATE{
        @Override
        public void apply(View view, Action action) {
            SkinRotateAnimator.getInstance().apply(view, action).start();
        }
    },
    ROTATE2{
        @Override
        public void apply(View view, Action action) {
            SkinRotateAnimator2.getInstance().apply(view, action).start();
        }
    },
    ROTATE3{
        @Override
        public void apply(View view, Action action) {
            SkinRotateAnimator3.getInstance().apply(view, action).start();
        }
    },
    ROTATE4{
        @Override
        public void apply(View view, Action action) {
            SkinRotateAnimator4.getInstance().apply(view, action).start();
        }
    },
    ROTATE5{
        @Override
        public void apply(View view, Action action) {
            SkinRotateHintAnimator.getInstance().apply(view, action).start();
        }
    },
    TRANSLATION{
        @Override
        public void apply(View view, Action action) {
            TranslationAnimator.getInstance().apply(view, action).start();
        }
    },
    TRANSLATION2{
        @Override
        public void apply(View view, Action action) {
            TranslationAnimator2.getInstance().apply(view, action).start();
        }
    },
    Scale{
        @Override
        public void apply(View view, Action action) {
            ScaleAnimator.getInstance().apply(view, action).start();
        }
    },
    Scale2{
        @Override
        public void apply(View view, Action action) {
            ScaleAnimator2.getInstance().apply(view, action).start();
        }
    };
    public abstract void apply(View view, Action action);
}
