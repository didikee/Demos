package com.didikee.demos.ui.tab;

import android.support.annotation.NonNull;
import android.view.animation.Interpolator;

/**
 * Created by didik on 2016/11/17.
 */

public class ValueAnimatorCompat {
    public interface AnimatorUpdateListener {
        /**
         * <p>Notifies the occurrence of another frame of the animation.</p>
         *
         * @param animator The animation which was repeated.
         */
        void onAnimationUpdate(com.didikee.demos.ui.tab.ValueAnimatorCompat animator);
    }

    /**
     * An animation listener receives notifications from an animation.
     * Notifications indicate animation related events, such as the end or the
     * repetition of the animation.
     */
    interface AnimatorListener {
        /**
         * <p>Notifies the start of the animation.</p>
         *
         * @param animator The started animation.
         */
        void onAnimationStart(com.didikee.demos.ui.tab.ValueAnimatorCompat animator);
        /**
         * <p>Notifies the end of the animation. This callback is not invoked
         * for animations with repeat count set to INFINITE.</p>
         *
         * @param animator The animation which reached its end.
         */
        void onAnimationEnd(com.didikee.demos.ui.tab.ValueAnimatorCompat animator);
        /**
         * <p>Notifies the cancellation of the animation. This callback is not invoked
         * for animations with repeat count set to INFINITE.</p>
         *
         * @param animator The animation which was canceled.
         */
        void onAnimationCancel(com.didikee.demos.ui.tab.ValueAnimatorCompat animator);
    }

    public static class AnimatorListenerAdapter implements com.didikee.demos.ui.tab.ValueAnimatorCompat.AnimatorListener {
        @Override
        public void onAnimationStart(com.didikee.demos.ui.tab.ValueAnimatorCompat animator) {
        }

        @Override
        public void onAnimationEnd(com.didikee.demos.ui.tab.ValueAnimatorCompat animator) {
        }

        @Override
        public void onAnimationCancel(com.didikee.demos.ui.tab.ValueAnimatorCompat animator) {
        }
    }

    public interface Creator {
        @NonNull
        com.didikee.demos.ui.tab.ValueAnimatorCompat createAnimator();
    }

    public static abstract class Impl {
        interface AnimatorUpdateListenerProxy {
            void onAnimationUpdate();
        }

        interface AnimatorListenerProxy {
            void onAnimationStart();
            void onAnimationEnd();
            void onAnimationCancel();
        }

        abstract void start();
        abstract boolean isRunning();
        abstract void setInterpolator(Interpolator interpolator);
        abstract void addListener(AnimatorListenerProxy listener);
        abstract void addUpdateListener(AnimatorUpdateListenerProxy updateListener);
        abstract void setIntValues(int from, int to);
        abstract int getAnimatedIntValue();
        abstract void setFloatValues(float from, float to);
        abstract float getAnimatedFloatValue();
        abstract void setDuration(long duration);
        abstract void cancel();
        abstract float getAnimatedFraction();
        abstract void end();
        abstract long getDuration();
    }

    private final com.didikee.demos.ui.tab.ValueAnimatorCompat.Impl mImpl;

    ValueAnimatorCompat(com.didikee.demos.ui.tab.ValueAnimatorCompat.Impl impl) {
        mImpl = impl;
    }

    public void start() {
        mImpl.start();
    }

    public boolean isRunning() {
        return mImpl.isRunning();
    }

    public void setInterpolator(Interpolator interpolator) {
        mImpl.setInterpolator(interpolator);
    }

    public void addUpdateListener(final com.didikee.demos.ui.tab.ValueAnimatorCompat.AnimatorUpdateListener updateListener) {
        if (updateListener != null) {
            mImpl.addUpdateListener(new com.didikee.demos.ui.tab.ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy() {
                @Override
                public void onAnimationUpdate() {
                    updateListener.onAnimationUpdate(com.didikee.demos.ui.tab.ValueAnimatorCompat.this);
                }
            });
        } else {
            mImpl.addUpdateListener(null);
        }
    }

    public void addListener(final com.didikee.demos.ui.tab.ValueAnimatorCompat.AnimatorListener listener) {
        if (listener != null) {
            mImpl.addListener(new com.didikee.demos.ui.tab.ValueAnimatorCompat.Impl.AnimatorListenerProxy() {
                @Override
                public void onAnimationStart() {
                    listener.onAnimationStart(com.didikee.demos.ui.tab.ValueAnimatorCompat.this);
                }

                @Override
                public void onAnimationEnd() {
                    listener.onAnimationEnd(com.didikee.demos.ui.tab.ValueAnimatorCompat.this);
                }

                @Override
                public void onAnimationCancel() {
                    listener.onAnimationCancel(com.didikee.demos.ui.tab.ValueAnimatorCompat.this);
                }
            });
        } else {
            mImpl.addListener(null);
        }
    }

    public void setIntValues(int from, int to) {
        mImpl.setIntValues(from, to);
    }

    public int getAnimatedIntValue() {
        return mImpl.getAnimatedIntValue();
    }

    public void setFloatValues(float from, float to) {
        mImpl.setFloatValues(from, to);
    }

    public float getAnimatedFloatValue() {
        return mImpl.getAnimatedFloatValue();
    }

    public void setDuration(long duration) {
        mImpl.setDuration(duration);
    }

    public void cancel() {
        mImpl.cancel();
    }

    public float getAnimatedFraction() {
        return mImpl.getAnimatedFraction();
    }

    public void end() {
        mImpl.end();
    }

    public long getDuration() {
        return mImpl.getDuration();
    }
}
