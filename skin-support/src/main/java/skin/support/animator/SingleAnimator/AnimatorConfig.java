package skin.support.animator.SingleAnimator;

import skin.support.animator.AnimatorType;

/**
 * Created by erfli on 2/27/17.
 */

public class AnimatorConfig {
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

    private AnimatorType textviewTextAnimationType;
    private AnimatorType textviewVisibleAnimationType;
    private AnimatorType imageviewVisibleAnimationType;

    private int animateConfig = 0;

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

    public boolean needTextViewAnimator() {
        return (animateConfig & TextViewAnimatorMask) > 0;
    }

    public boolean needImageViewAnimator() {
        return (animateConfig & ImageViewAnimatorMask) > 0;
    }

    public AnimatorType getTextviewTextAnimationType() {
        return textviewTextAnimationType;
    }

    public AnimatorType getTextviewVisibleAnimationType() {
        return textviewVisibleAnimationType;
    }

    public AnimatorType getImageviewVisibleAnimationType() {
        return imageviewVisibleAnimationType;
    }

    public static final class Builder {
        private AnimatorType textviewTextAnimationType;
        private AnimatorType textviewVisibleAnimationType;
        private AnimatorType imageviewVisibleAnimationType;

        public Builder() {
        }

        public Builder textviewTextAnimationType(AnimatorType val) {
            textviewTextAnimationType = val;
            return this;
        }

        public Builder textviewVisibleAnimationType(AnimatorType val) {
            textviewVisibleAnimationType = val;
            return this;
        }

        public Builder imageviewVisibleAnimationType(AnimatorType val) {
            imageviewVisibleAnimationType = val;
            return this;
        }

        public AnimatorConfig build() {

            return new AnimatorConfig(this);
        }
    }
}
