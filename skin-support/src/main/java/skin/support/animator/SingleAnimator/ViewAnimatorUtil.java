package skin.support.animator.SingleAnimator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import skin.support.animator.Action;

/**
 * Created by erfli on 2/27/17.
 */

public class ViewAnimatorUtil {
    private ViewAnimatorUtil(){}
    public static void executeAnimator(@NonNull View view, @NonNull ViewAnimatorType animatorType, @Nullable Action action) {
        animatorType.apply(view, action);
    }
}
