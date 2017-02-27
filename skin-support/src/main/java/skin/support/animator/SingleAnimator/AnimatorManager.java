package skin.support.animator.SingleAnimator;

/**
 * Created by erfli on 2/27/17.
 */

public class AnimatorManager {
    private static AnimatorConfig config;

    public static AnimatorConfig getConfig() {
        return config;
    }

    public static void setConfig(AnimatorConfig animatorConfig) {
        config = animatorConfig;
    }
}
