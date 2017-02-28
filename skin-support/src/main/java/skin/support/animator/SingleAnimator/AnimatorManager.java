package skin.support.animator.SingleAnimator;

/**
 * Created by erfli on 2/27/17.
 */

public class AnimatorManager {
    private static AnimatorConfig config = new AnimatorConfig.Builder().build();

    public static AnimatorConfig getConfig() {
        return config;
    }

    public static void setConfig(AnimatorConfig animatorConfig) {
        config = animatorConfig;
    }

    public static void openAnimator(){
        config.openAnimator();
    }

    public static void closeAnimator(){
        config.closeAnimator();
    }

}
