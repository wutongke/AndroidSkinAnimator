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
            SkinAlphaRotateAnimator.getInstance().apply(view, action).start();
        }
    };
    public abstract void apply(View view, Action action);
}
