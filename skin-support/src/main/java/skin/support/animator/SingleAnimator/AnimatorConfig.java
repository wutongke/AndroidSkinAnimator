package skin.support.animator.SingleAnimator;

import android.text.TextUtils;

import skin.support.animator.AnimatorType;

/**
 * Created by erfli on 2/27/17.
 */

public class AnimatorConfig {
    private static final int Open = 0xffffffff;
    private static final int Off = 0;
    private static final int MaskLength = 3;
    private static final int Mask = 0b111;
    private static final int Mask_First = 0b001;
    private static final int Mask_Second = 0b010;
    private static final int Mask_Third = 0b100;

    public static final int TextViewAnimatorMask = Mask;
    private static final int TextViewSetVisibleAnimator = Mask_First;
    private static final int TextViewSetTextAnimator = Mask_Second;

    public static final int ImageViewAnimatorMask = Mask >> MaskLength;
    private static final int ImageViewSetVisibleAnimator = Mask_First >> MaskLength;

    private ViewAnimatorType textviewTextAnimationType = ViewAnimatorType.None;
    private ViewAnimatorType textviewVisibleAnimationType = ViewAnimatorType.None;
    private ViewAnimatorType imageviewVisibleAnimationType = ViewAnimatorType.None;

    private int animateConfig = 0;
    private int Toggle = Off;

    private AnimatorConfig(Builder builder) {
        textviewTextAnimationType = builder.textviewTextAnimationType;
        textviewVisibleAnimationType = builder.textviewVisibleAnimationType;
        imageviewVisibleAnimationType = builder.imageviewVisibleAnimationType;
        if (textviewTextAnimationType != null) {
            animateConfig |= TextViewSetTextAnimator;
        }

        if (textviewVisibleAnimationType != null) {
            animateConfig |= TextViewSetVisibleAnimator;
        }

        if (imageviewVisibleAnimationType != null) {
            animateConfig |= ImageViewSetVisibleAnimator;
        }
    }

    void openAnimator(){
        Toggle = Open;
    }

    void closeAnimator(){
        Toggle = Off;
    }

    public boolean needTextViewAnimator() {
        return (animateConfig & TextViewAnimatorMask) > 0;
    }

    public boolean needImageViewAnimator() {
        return (animateConfig & ImageViewAnimatorMask) > 0;
    }

    public ViewAnimatorType getTextViewTextAnimationType() {
        return checkAnimatorOpen(textviewTextAnimationType);
    }

    public ViewAnimatorType getTextViewVisibleAnimationType() {
        return checkAnimatorOpen(textviewVisibleAnimationType);
    }

    private ViewAnimatorType checkAnimatorOpen(ViewAnimatorType animatorType){
        if(Toggle == Open){
            return animatorType;
        }else{
            return ViewAnimatorType.None;
        }
    }

    public ViewAnimatorType getImageviewVisibleAnimationType() {
        return imageviewVisibleAnimationType;
    }

    public static final class Builder {
        private ViewAnimatorType textviewTextAnimationType = ViewAnimatorType.None;
        private ViewAnimatorType textviewVisibleAnimationType = ViewAnimatorType.None;
        private ViewAnimatorType imageviewVisibleAnimationType = ViewAnimatorType.None;

        public Builder() {
        }

        public Builder textviewTextAnimationType(ViewAnimatorType val) {
            textviewTextAnimationType = val;
            return this;
        }

        public Builder textviewVisibleAnimationType(ViewAnimatorType val) {
            textviewVisibleAnimationType = val;
            return this;
        }

        public Builder imageviewVisibleAnimationType(ViewAnimatorType val) {
            imageviewVisibleAnimationType = val;
            return this;
        }

        public AnimatorConfig build() {

            return new AnimatorConfig(this);
        }
    }
}
