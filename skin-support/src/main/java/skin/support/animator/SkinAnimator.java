package skin.support.animator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by erfli on 2/25/17.
 */

public interface SkinAnimator {
    int PRE_DURATION = 200;
    int AFTER_DURATION = 200;

    SkinAnimator apply(@NonNull View view, @Nullable Action action);

    SkinAnimator setPreDuration();

    SkinAnimator setAfterDuration();

    SkinAnimator setDuration();

    void start();

}
