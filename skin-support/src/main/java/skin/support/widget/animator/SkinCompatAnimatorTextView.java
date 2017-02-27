package skin.support.widget.animator;

import android.content.Context;
import android.util.AttributeSet;

import skin.support.animator.Action;
import skin.support.animator.SingleAnimator.AnimatorManager;
import skin.support.animator.SingleAnimator.SingleAnimatorUtil;
import skin.support.widget.SkinCompatTextView;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatAnimatorTextView extends SkinCompatTextView {
    private Action textAction;
    private Action visibleAction;
    private CharSequence text;
    private BufferType type;
    private int visibleStatus = VISIBLE;

    public SkinCompatAnimatorTextView(Context context) {
        this(context, null);
    }

    public SkinCompatAnimatorTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public SkinCompatAnimatorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textAction = new Action() {
            @Override
            public void action() {
                SkinCompatAnimatorTextView.this.updateText(text, type);
            }
        };
        visibleAction = new Action() {
            @Override
            public void action() {
                SkinCompatAnimatorTextView.this.updateVisibility(visibleStatus);
            }
        };
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        if (AnimatorManager.getConfig().getTextviewTextAnimationType() == null) {
            updateText(text, type);
            return;
        }
        if (this.type != null && this.text != null) {
            SingleAnimatorUtil.executeAnimator(this, AnimatorManager.getConfig().getTextviewTextAnimationType(), textAction);
        } else {
            updateText(text, type);
        }
        this.text = text;
        this.type = type;
    }

    private void updateText(final CharSequence text, final BufferType type) {
        super.setText(text, type);
    }

    @Override
    public void setVisibility(int visibility) {

        if (AnimatorManager.getConfig().getTextviewVisibleAnimationType() == null) {
            super.setVisibility(visibility);
        } else {
            this.visibleStatus = visibility;
            SingleAnimatorUtil.executeAnimator(this, AnimatorManager.getConfig().getTextviewVisibleAnimationType(), visibleAction);
        }

    }

    private void updateVisibility(int visibleStatus) {
        super.setVisibility(visibleStatus);
    }

}
