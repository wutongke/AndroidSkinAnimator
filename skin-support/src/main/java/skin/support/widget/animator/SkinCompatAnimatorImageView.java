package skin.support.widget.animator;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import skin.support.animator.Action;
import skin.support.animator.SingleAnimator.AnimatorManager;
import skin.support.animator.SingleAnimator.ViewAnimatorType;
import skin.support.animator.SingleAnimator.ViewAnimatorUtil;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatImageHelper;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatAnimatorImageView extends AppCompatImageView implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private SkinCompatImageHelper mImageHelper;
    private Action visibleAction;
    private int visibleStatus = VISIBLE;


    public SkinCompatAnimatorImageView(Context context) {
        this(context, null);
    }

    public SkinCompatAnimatorImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatAnimatorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);

        mImageHelper = new SkinCompatImageHelper(this);
        mImageHelper.loadFromAttributes(attrs, defStyleAttr);
        visibleAction = new Action() {
            @Override
            public void action() {
                SkinCompatAnimatorImageView.this.updateVisibility(visibleStatus);
            }
        };
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        // Intercept this call and instead retrieve the Drawable via the image helper
        if (mImageHelper != null) {
            mImageHelper.setImageResource(resId);
        }
    }

    @Override
    public void setVisibility(int visibility) {

        if (AnimatorManager.getConfig().getImageViewVisibleAnimationType() == ViewAnimatorType.None) {
            super.setVisibility(visibility);
        } else {
            this.visibleStatus = visibility;
            if (visibility == GONE) {
                ViewAnimatorUtil.executeAnimator(this, AnimatorManager.getConfig().getImageViewVisibleAnimationType(), visibleAction);
            } else {
                visibleAction.action();
            }
        }

    }

    private void updateVisibility(int visibleStatus) {
        super.setVisibility(visibleStatus);
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        if (mImageHelper != null) {
            mImageHelper.applySkin();
        }
    }
}
