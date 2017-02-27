package skin.support.animator.SingleAnimator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import skin.support.animator.Action;
import skin.support.animator.AnimatorType;

/**
 * Created by erfli on 2/27/17.
 */

public class SingleAnimatorUtil {
    private SingleAnimatorUtil(){}
    public static void executeAnimator(@NonNull View view, @NonNull AnimatorType animatorType, @Nullable Action action) {
        animatorType.apply(view, action);
    }
}
