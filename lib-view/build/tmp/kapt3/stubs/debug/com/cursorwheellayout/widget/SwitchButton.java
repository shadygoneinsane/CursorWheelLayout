package com.cursorwheellayout.widget;

/**
 * Created by chensuilun on 16-4-13.
 */
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 K2\u00020\u0001:\u0003IJKB\'\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tB+\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\u000bJ\u001a\u0010%\u001a\u00020&2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J\u0018\u0010\'\u001a\u00020&2\u0006\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u0007H\u0014J\b\u0010*\u001a\u00020&H\u0014J\b\u0010+\u001a\u00020&H\u0014J\b\u0010,\u001a\u00020\u0007H\u0014J\b\u0010-\u001a\u00020\u0007H\u0014J\u0006\u0010.\u001a\u00020&J\u0010\u0010/\u001a\u00020&2\b\u00100\u001a\u0004\u0018\u00010\u001aJ\b\u00106\u001a\u00020\u0017H\u0002J\b\u00107\u001a\u00020\u0017H\u0016J\b\u00108\u001a\u00020&H\u0002J\u0010\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020\u0017H\u0002J\b\u0010;\u001a\u00020\u0007H\u0002J\u0010\u0010@\u001a\u00020&2\u0006\u0010A\u001a\u00020BH\u0014J\u000e\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020\u0007J\n\u0010E\u001a\u0004\u0018\u00010FH\u0016J\u0010\u0010G\u001a\u00020&2\u0006\u0010H\u001a\u00020FH\u0016R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00078\u0002X\u0083D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R$\u00101\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u00078F@BX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b2\u00103\"\u0004\b4\u00105R$\u0010:\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u00178B@BX\u0082\u000e\u00a2\u0006\f\u001a\u0004\b:\u0010=\"\u0004\b>\u0010?\u00a8\u0006L"}, d2 = {"Lcom/cursorwheellayout/widget/SwitchButton;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mDrawableGravity", "mBroadPaint", "Landroid/graphics/Paint;", "mCoverPaint", "mRevealBgPaint", "mBoardColor", "mBoardWidth", "mCheckRevealColor", "mUnCheckRevealColor", "mProgress", "mChecked", "", "mBroadcasting", "mOnCheckedChangeListener", "Lcom/cursorwheellayout/widget/SwitchButton$OnCheckedChangeListener;", "mUncheckDrawable", "Landroid/graphics/drawable/Drawable;", "mCheckedDrawable", "mArgbEvaluator", "Landroid/animation/ArgbEvaluator;", "mFraction", "", "mCheckAnim", "Landroid/animation/ObjectAnimator;", "mAttachedToWindow", "init", "", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onAttachedToWindow", "onDetachedFromWindow", "getSuggestedMinimumWidth", "getSuggestedMinimumHeight", "toggle", "setOnCheckedChangeListener", "listener", "progress", "getProgress", "()I", "setProgress", "(I)V", "onDoubClick", "performClick", "cancelAnim", "addAnim", "isChecked", "generateCurColor", "checked", "()Z", "setChecked", "(Z)V", "onDraw", "canvas", "Landroid/graphics/Canvas;", "setDrawableGravity", "drawableGravity", "onSaveInstanceState", "Landroid/os/Parcelable;", "onRestoreInstanceState", "state", "OnCheckedChangeListener", "SavedState", "Companion", "lib-view_debug"})
public final class SwitchButton extends android.view.View {
    private int mDrawableGravity = -1;
    
    /**
     * 画边界
     */
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Paint mBroadPaint;
    
    /**
     * 覆盖层
     */
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Paint mCoverPaint;
    
    /**
     * 画扩散进度
     */
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Paint mRevealBgPaint;
    
    /**
     * 边框颜色
     */
    @androidx.annotation.ColorInt()
    private final int mBoardColor = -12826286;
    
    /**
     * 边框厚度
     */
    private int mBoardWidth = 0;
    
    /**
     * 选中的时候的颜色
     */
    private int mCheckRevealColor = -12826286;
    
    /**
     * 未选中时候的颜色
     */
    private int mUnCheckRevealColor = -12826286;
    
    /**
     * 用于动画 0-100 选中的时候;
     */
    private int mProgress = 0;
    private boolean mChecked = false;
    private boolean mBroadcasting = false;
    @org.jetbrains.annotations.Nullable()
    private com.cursorwheellayout.widget.SwitchButton.OnCheckedChangeListener mOnCheckedChangeListener;
    
    /**
     * 未选中的时候的资源ID
     */
    @org.jetbrains.annotations.Nullable()
    private android.graphics.drawable.Drawable mUncheckDrawable;
    
    /**
     * 未选中的时候的资源ID
     */
    @org.jetbrains.annotations.Nullable()
    private android.graphics.drawable.Drawable mCheckedDrawable;
    @org.jetbrains.annotations.Nullable()
    private android.animation.ArgbEvaluator mArgbEvaluator;
    private float mFraction = 0.0F;
    @org.jetbrains.annotations.Nullable()
    private android.animation.ObjectAnimator mCheckAnim;
    private boolean mAttachedToWindow = false;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "SwitchButton";
    private static final int PROGRESS_MIN = 0;
    private static final int PROGRESS_MAX = 100;
    private static final int DEFAULT_BOARD_WIDTH = 4;
    private static final int DEFAULT_BOARD_COLOR = -12826286;
    private static final int DEFAULT_UNCHECK_REVEAL_COLOR = -12826286;
    public static final int DRAWABLE_GRAVITY_LEFT = 0;
    public static final int DRAWABLE_GRAVITY_RIGHT = 1;
    public static final int DEFAULT_DRAWABLE_GRAVITY = -1;
    private static final int DEFAULT_CHECK_REVEAL_COLOR = -15062;
    private static final int DEFAULT_DISABLE_COVER_COLOR = 1291845631;
    private static final int DEFAULT_CHECK_DRAWABLE_ID = com.cursorwheel.view.R.drawable.ic_switch_on;
    private static final int DEFAULT_UNCHECK_DRAWABLE_ID = com.cursorwheel.view.R.drawable.ic_switch_off;
    private static final int DEFAULT_SIZE = 126;
    private static long slastTime = 0L;
    @org.jetbrains.annotations.NotNull()
    public static final com.cursorwheellayout.widget.SwitchButton.Companion Companion = null;
    
    @kotlin.jvm.JvmOverloads()
    public SwitchButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public SwitchButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public SwitchButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @android.annotation.TargetApi(value = android.os.Build.VERSION_CODES.LOLLIPOP)
    public SwitchButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(null);
    }
    
    private final void init(android.content.Context context, android.util.AttributeSet attrs) {
    }
    
    @java.lang.Override()
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    }
    
    @java.lang.Override()
    protected void onAttachedToWindow() {
    }
    
    @java.lang.Override()
    protected void onDetachedFromWindow() {
    }
    
    @java.lang.Override()
    protected int getSuggestedMinimumWidth() {
        return 0;
    }
    
    @java.lang.Override()
    protected int getSuggestedMinimumHeight() {
        return 0;
    }
    
    public final void toggle() {
    }
    
    public final void setOnCheckedChangeListener(@org.jetbrains.annotations.Nullable()
    com.cursorwheellayout.widget.SwitchButton.OnCheckedChangeListener listener) {
    }
    
    public final int getProgress() {
        return 0;
    }
    
    private final void setProgress(int progress) {
    }
    
    private final boolean onDoubClick() {
        return false;
    }
    
    @java.lang.Override()
    public boolean performClick() {
        return false;
    }
    
    private final void cancelAnim() {
    }
    
    private final void addAnim(boolean isChecked) {
    }
    
    private final int generateCurColor() {
        return 0;
    }
    
    private final boolean isChecked() {
        return false;
    }
    
    private final void setChecked(boolean checked) {
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    public final void setDrawableGravity(int drawableGravity) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.Parcelable onSaveInstanceState() {
        return null;
    }
    
    @java.lang.Override()
    public void onRestoreInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Parcelable state) {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001d"}, d2 = {"Lcom/cursorwheellayout/widget/SwitchButton$Companion;", "", "<init>", "()V", "TAG", "", "PROGRESS_MIN", "", "PROGRESS_MAX", "DEFAULT_BOARD_WIDTH", "DEFAULT_BOARD_COLOR", "DEFAULT_UNCHECK_REVEAL_COLOR", "DRAWABLE_GRAVITY_LEFT", "DRAWABLE_GRAVITY_RIGHT", "DEFAULT_DRAWABLE_GRAVITY", "DEFAULT_CHECK_REVEAL_COLOR", "DEFAULT_DISABLE_COVER_COLOR", "DEFAULT_CHECK_DRAWABLE_ID", "DEFAULT_UNCHECK_DRAWABLE_ID", "DEFAULT_SIZE", "resolveSizeAndState", "desireSize", "measureSpec", "slastTime", "", "getSlastTime", "()J", "setSlastTime", "(J)V", "lib-view_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final int resolveSizeAndState(int desireSize, int measureSpec) {
            return 0;
        }
        
        public final long getSlastTime() {
            return 0L;
        }
        
        public final void setSlastTime(long p0) {
        }
    }
    
    /**
     * Interface definition for a callback to be invoked when the checked state
     * of a compound button changed.
     */
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/cursorwheellayout/widget/SwitchButton$OnCheckedChangeListener;", "", "onCheckedChanged", "", "buttonView", "Lcom/cursorwheellayout/widget/SwitchButton;", "isChecked", "", "lib-view_debug"})
    public static abstract interface OnCheckedChangeListener {
        
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        public abstract void onCheckedChanged(@org.jetbrains.annotations.Nullable()
        com.cursorwheellayout.widget.SwitchButton buttonView, boolean isChecked);
    }
    
    /**
     * @author chensuilun
     */
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u00a8\u0006\u0014"}, d2 = {"Lcom/cursorwheellayout/widget/SwitchButton$SavedState;", "Landroid/view/View$BaseSavedState;", "superState", "Landroid/os/Parcelable;", "<init>", "(Landroid/os/Parcelable;)V", "mChecked", "", "getMChecked", "()Z", "setMChecked", "(Z)V", "writeToParcel", "", "out", "Landroid/os/Parcel;", "flags", "", "toString", "", "lib-view_debug"})
    public static final class SavedState extends android.view.View.BaseSavedState {
        private boolean mChecked = false;
        
        public SavedState(@org.jetbrains.annotations.Nullable()
        android.os.Parcelable superState) {
            super(null);
        }
        
        public final boolean getMChecked() {
            return false;
        }
        
        public final void setMChecked(boolean p0) {
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel out, int flags) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}