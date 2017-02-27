package skin.support.animator.activityAnimator;

import android.view.View;

import skin.support.animator.Action;
import skin.support.animator.AnimatorType;

/**
 * Created by erfli on 2/26/17.
 */

public class SkinActivityAnimator {
    private static AnimatorType ACTIVITYANIMATORTYPE = AnimatorType.ALPHA;

    public static void configActivityAnimatorType(AnimatorType animatorType){
        ACTIVITYANIMATORTYPE = animatorType;
    }

    public static void updateSkin(View view, Action action){
        ACTIVITYANIMATORTYPE.apply(view, action);
    }
}
