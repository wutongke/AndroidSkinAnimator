package skin.support.animator.activityAnimator;

import android.view.View;

import skin.support.animator.Action;
import skin.support.animator.ActivityAnimatorType;

/**
 * Created by erfli on 2/26/17.
 */

public class SkinActivityAnimator {
    private static ActivityAnimatorType ACTIVITYANIMATORTYPE = ActivityAnimatorType.ALPHA;

    public static void configActivityAnimatorType(ActivityAnimatorType activityAnimatorType){
        ACTIVITYANIMATORTYPE = activityAnimatorType;
    }

    public static void updateSkin(View view, Action action){
        ACTIVITYANIMATORTYPE.apply(view, action);
    }
}
