package com.cursorwheel;

/**
 * The base cycle wheel menu layout with cursor
 *
 * @author chensuilun
 * @attr R.styleable.CursorWheelLayout_wheelSelectedAngle
 * @attr R.styleable.CursorWheelLayout_wheelPaddingRadio
 * @attr R.styleable.CursorWheelLayout_wheelCenterRadio
 * @attr R.styleable.CursorWheelLayout_wheelItemRadio
 * @attr R.styleable.CursorWheelLayout_wheelFlingValue
 * @attr R.styleable.CursorWheelLayout_wheelCursorColor
 * @attr R.styleable.CursorWheelLayout_wheelCursorHeight
 * @attr R.styleable.CursorWheelLayout_wheelItemRotateMode
 * @attr R.styleable.CursorWheelLayout_wheelGuideLineWidth
 * @attr R.styleable.CursorWheelLayout_wheelGuideLineColor
 */
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 \u008e\u00012\u00020\u0001:\u000e\u0088\u0001\u0089\u0001\u008a\u0001\u008b\u0001\u008c\u0001\u008d\u0001\u008e\u0001B\'\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tB+\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\u000bJ\u001a\u0010D\u001a\u00020E2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010F\u001a\u00020EH\u0002J\u0018\u0010G\u001a\u00020E2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u0007H\u0014J\u0018\u0010J\u001a\u00020\u00072\u0006\u0010K\u001a\u00020\u00072\u0006\u0010L\u001a\u00020\u0007H\u0002J\b\u0010P\u001a\u00020EH\u0002J\u0010\u0010Q\u001a\u00020E2\b\u00101\u001a\u0004\u0018\u000102J\u000e\u0010R\u001a\u00020E2\u0006\u0010S\u001a\u000204J\u000e\u0010T\u001a\u00020E2\u0006\u0010U\u001a\u00020\u0019J\u0006\u0010V\u001a\u00020\u0019J0\u0010W\u001a\u00020E2\u0006\u0010X\u001a\u00020\u00192\u0006\u0010Y\u001a\u00020\u00072\u0006\u0010Z\u001a\u00020\u00072\u0006\u0010[\u001a\u00020\u00072\u0006\u0010\\\u001a\u00020\u0007H\u0014J(\u0010]\u001a\u00020E2\u0006\u0010^\u001a\u00020\u00072\u0006\u0010_\u001a\u00020\u00072\u0006\u0010`\u001a\u00020\u00072\u0006\u0010a\u001a\u00020\u0007H\u0014J\b\u0010b\u001a\u00020EH\u0016J\u0010\u0010c\u001a\u00020E2\u0006\u0010d\u001a\u00020eH\u0014J\u0010\u0010f\u001a\u00020E2\u0006\u0010d\u001a\u00020eH\u0014J\u0010\u0010g\u001a\u00020\u00192\u0006\u0010h\u001a\u00020iH\u0016J\u0018\u0010j\u001a\u00020\u00192\u0006\u0010k\u001a\u00020\u000f2\u0006\u0010l\u001a\u00020\u000fH\u0002J\u0010\u0010m\u001a\u00020\u00192\u0006\u0010h\u001a\u00020iH\u0016J\u0018\u0010n\u001a\u00020\u000f2\u0006\u0010o\u001a\u00020\u000f2\u0006\u0010p\u001a\u00020\u000fH\u0002J\u0018\u0010q\u001a\u00020\u00072\u0006\u0010k\u001a\u00020\u000f2\u0006\u0010l\u001a\u00020\u000fH\u0002J\u0010\u0010r\u001a\u00020E2\b\u0010s\u001a\u0004\u0018\u00010\u0013J\b\u0010t\u001a\u00020EH\u0002J\b\u0010u\u001a\u00020EH\u0002J\u000e\u0010v\u001a\u00020E2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010y\u001a\u00020E2\u0006\u0010z\u001a\u00020\u0019H\u0002J\u0012\u0010z\u001a\u00020E2\b\b\u0002\u0010{\u001a\u00020\u0019H\u0002J\b\u0010|\u001a\u00020EH\u0002J\u000e\u0010}\u001a\u00020E2\u0006\u0010~\u001a\u00020\u0007J\u0016\u0010}\u001a\u00020E2\u0006\u0010~\u001a\u00020\u00072\u0006\u0010\u007f\u001a\u00020\u0019J\u0010\u0010\u0080\u0001\u001a\u00020E2\u0007\u0010\u0081\u0001\u001a\u00020\u0011J\t\u0010\u0082\u0001\u001a\u00020EH\u0014J\u0014\u0010\u0083\u0001\u001a\u00020E2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u001fH\u0014J\u0014\u0010\u0085\u0001\u001a\u00020E2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u001fH\u0014R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\'\u001a\u00060(R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u00106\u001a\u00020\u00078\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u00107\u001a\u00020\u00078\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020-X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020-X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010M\u001a\u0004\u0018\u00010\u001f8F\u00a2\u0006\u0006\u001a\u0004\bN\u0010OR\u0014\u0010w\u001a\u00020\u00078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bx\u0010#R\u0016\u0010\u0086\u0001\u001a\t\u0018\u00010\u0087\u0001R\u00020\u0000X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u008f\u0001"}, d2 = {"Lcom/cursorwheel/CursorWheelView;", "Landroid/view/ViewGroup;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mRootDiameter", "mFlingableValue", "mPadding", "", "mStartAngle", "", "mWheelAdapter", "Lcom/cursorwheel/CursorWheelView$CycleWheelAdapter;", "mMenuItemCount", "mTmpAngle", "mDownTime", "", "mIsFling", "", "mIsDraging", "mLastX", "mLastY", "mSelectedAngle", "mSelectedView", "Landroid/view/View;", "value", "selectedPosition", "getSelectedPosition", "()I", "mTempSelectedView", "mTempSelectedPosition", "mNeedSlotIntoCenter", "mFlingRunnable", "Lcom/cursorwheel/CursorWheelView$FlingRunnable;", "mCursorPaint", "Landroid/graphics/Paint;", "mWheelPaint", "mTrianglePath", "Landroid/graphics/Path;", "mTriangleHeight", "mGuideLineWidth", "mGuideLineColor", "mOnMenuItemClickListener", "Lcom/cursorwheel/CursorWheelView$OnMenuItemClickListener;", "mOnMenuSelectedListener", "Lcom/cursorwheel/CursorWheelView$OnMenuSelectedListener;", "mIsFirstLayout", "mWheelBgColor", "mCursorColor", "mMenuRadioDimension", "mCenterRadioDimension", "mPaddingRadio", "mWheelBgPath", "mBgMatrix", "Landroid/graphics/Matrix;", "mBgRegion", "Landroid/graphics/Region;", "mGuidePath", "mGuidePaint", "itemRotateMode", "mHapticFeedbackEnabled", "initWheel", "", "init", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "resolveSizeAndState", "desireSize", "measureSpec", "centerItem", "getCenterItem", "()Landroid/view/View;", "initTriangle", "setOnMenuItemClickListener", "setOnMenuSelectedListener", "onMenuSelectedListener", "setWheelHapticFeedbackEnabled", "enabled", "isWheelHapticFeedbackEnabled", "onLayout", "changed", "l", "t", "r", "b", "onSizeChanged", "w", "h", "oldw", "oldh", "requestLayout", "onDraw", "canvas", "Landroid/graphics/Canvas;", "dispatchDraw", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "isEventInWheel", "x", "y", "onTouchEvent", "getAngle", "xTouch", "yTouch", "getQuadrant", "setAdapter", "adapter", "onDateSetChanged", "addMenuItems", "setPadding", "defaultWidth", "getDefaultWidth", "endFling", "scrollIntoSlots", "showAnimation", "selectionChangeCallback", "setSelection", "position", "animation", "setSelectedAngle", "selectedAngle", "onDetachedFromWindow", "onInnerItemSelected", "v", "onInnerItemUnselected", "mWheelDataSetObserver", "Lcom/cursorwheel/CursorWheelView$WheelDataSetObserver;", "FlingRunnable", "OnMenuItemClickListener", "InnerClickListener", "OnMenuSelectedListener", "WheelDataSetObserver", "CycleWheelAdapter", "Companion", "lib-view_debug"})
public class CursorWheelView extends android.view.ViewGroup {
    
    /**
     * CircleMenuLayout 's size
     */
    private int mRootDiameter = 0;
    
    /**
     * Angle a touch can wander before we think the user is flinging
     */
    private final int mFlingableValue = 300;
    private float mPadding = 0.0F;
    private double mStartAngle = 0.0;
    
    /**
     * menu 's source data
     */
    @org.jetbrains.annotations.Nullable()
    private com.cursorwheel.CursorWheelView.CycleWheelAdapter mWheelAdapter;
    
    /**
     */
    private int mMenuItemCount = 0;
    
    /**
     */
    private float mTmpAngle = 0.0F;
    
    /**
     */
    private long mDownTime = 0L;
    
    /**
     * weather is fling now
     */
    private boolean mIsFling = false;
    
    /**
     */
    private boolean mIsDraging = false;
    
    /**
     */
    private float mLastX = 0.0F;
    private float mLastY = 0.0F;
    
    /**
     * [0,360)
     */
    private double mSelectedAngle = 0.0;
    
    /**
     * The currently selected item's child.
     */
    @org.jetbrains.annotations.Nullable()
    private android.view.View mSelectedView;
    
    /**
     * The position of the selected View
     */
    private int selectedPosition = -1;
    
    /**
     * The temp selected item's child.
     */
    @org.jetbrains.annotations.Nullable()
    private android.view.View mTempSelectedView;
    
    /**
     * The position of the temp selected item's child.
     */
    private int mTempSelectedPosition = -1;
    
    /**
     * Determine whether scrolling to center is needed. Sometimes the selected angle
     * is the same as the layout center angle, so no additional movement is needed
     */
    private boolean mNeedSlotIntoCenter = false;
    @org.jetbrains.annotations.NotNull()
    private final com.cursorwheel.CursorWheelView.FlingRunnable mFlingRunnable = null;
    
    /**
     * draw cursor
     */
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Paint mCursorPaint;
    
    /**
     * draw wheel bg
     */
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Paint mWheelPaint;
    
    /**
     * path of cursor
     */
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Path mTrianglePath;
    private int mTriangleHeight = 0;
    private int mGuideLineWidth = 0;
    private int mGuideLineColor = 0;
    
    /**
     * callback on menu item being click
     */
    @org.jetbrains.annotations.Nullable()
    private com.cursorwheel.CursorWheelView.OnMenuItemClickListener mOnMenuItemClickListener;
    
    /**
     * callback on menu item being selected
     */
    @org.jetbrains.annotations.Nullable()
    private com.cursorwheel.CursorWheelView.OnMenuSelectedListener mOnMenuSelectedListener;
    private boolean mIsFirstLayout = true;
    @androidx.annotation.ColorInt()
    private int mWheelBgColor = 0;
    @androidx.annotation.ColorInt()
    private int mCursorColor = 0;
    private float mMenuRadioDimension = 0.0F;
    private float mCenterRadioDimension = 0.0F;
    private float mPaddingRadio = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Path mWheelBgPath = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Matrix mBgMatrix = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Region mBgRegion = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Path mGuidePath = null;
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Paint mGuidePaint;
    private int itemRotateMode = 0;
    
    /**
     * Enable haptic feedback during scrolling
     */
    private boolean mHapticFeedbackEnabled = true;
    @org.jetbrains.annotations.Nullable()
    private com.cursorwheel.CursorWheelView.WheelDataSetObserver mWheelDataSetObserver;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "CircleMenuLayout";
    
    /**
     * size of menu item relative to parent
     */
    private static final float RADIO_DEFAULT_CHILD_DIMENSION = 0.25F;
    
    /**
     * size of center item relative to parent
     */
    private static final float RADIO_DEFAULT_CENTER_DIMENSION = 0.33333334F;
    
    /**
     * ignore the origin padding ,real padding size determine by parent size
     */
    private static final float RADIO_PADDING_LAYOUT = 0.083333336F;
    private static final int INVALID_POSITION = -1;
    private static final float DEFAULT_SELECTED_ANGLE = 0.0F;
    
    /**
     * Angle a touch can wander before we think the user is flinging
     */
    private static final int FLINGABLE_VALUE = 300;
    
    /**
     */
    private static final int NOCLICK_VALUE = 3;
    
    /**
     * default cursor color
     */
    @androidx.annotation.ColorInt()
    private static final int DEFAULT_CURSOR_COLOR = -15062;
    
    /**
     * default wheel background color
     */
    @androidx.annotation.ColorInt()
    private static final int DEFAULT_WHEEL_BG_COLOR = -451733732;
    @androidx.annotation.ColorInt()
    private static final int DEFAULT_GUIDE_LINE_COLOR = -9276814;
    public static final int DEFAULT_TRIANGLE_HEIGHT = 13;
    public static final int DEFAULT_GUIDE_LINE_WIDTH = 0;
    
    /**
     * Don't rotate my item.DEFAULT
     */
    public static final int ITEM_ROTATE_MODE_NONE = 0;
    public static final int ITEM_ROTATE_MODE_INWARD = 1;
    public static final int ITEM_ROTATE_MODE_OUTWARD = 2;
    @org.jetbrains.annotations.NotNull()
    public static final com.cursorwheel.CursorWheelView.Companion Companion = null;
    
    /**
     * The position of the selected View
     */
    public final int getSelectedPosition() {
        return 0;
    }
    
    @kotlin.jvm.JvmOverloads()
    public CursorWheelView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public CursorWheelView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public CursorWheelView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @android.annotation.TargetApi(value = android.os.Build.VERSION_CODES.LOLLIPOP)
    public CursorWheelView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(null);
    }
    
    private final void initWheel(android.content.Context context, android.util.AttributeSet attrs) {
    }
    
    private final void init() {
    }
    
    @java.lang.Override()
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    }
    
    private final int resolveSizeAndState(int desireSize, int measureSpec) {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.view.View getCenterItem() {
        return null;
    }
    
    /**
     * init the triangle path
     */
    private final void initTriangle() {
    }
    
    /**
     * @param mOnMenuItemClickListener
     */
    public final void setOnMenuItemClickListener(@org.jetbrains.annotations.Nullable()
    com.cursorwheel.CursorWheelView.OnMenuItemClickListener mOnMenuItemClickListener) {
    }
    
    public final void setOnMenuSelectedListener(@org.jetbrains.annotations.NotNull()
    com.cursorwheel.CursorWheelView.OnMenuSelectedListener onMenuSelectedListener) {
    }
    
    /**
     * Enable haptic feedback during scrolling
     */
    public final void setWheelHapticFeedbackEnabled(boolean enabled) {
    }
    
    /**
     * @return true if haptic feedback is enabled
     */
    public final boolean isWheelHapticFeedbackEnabled() {
        return false;
    }
    
    @java.lang.Override()
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }
    
    @java.lang.Override()
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    }
    
    @java.lang.Override()
    public void requestLayout() {
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    @java.lang.Override()
    protected void dispatchDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    @java.lang.Override()
    public boolean dispatchTouchEvent(@org.jetbrains.annotations.NotNull()
    android.view.MotionEvent event) {
        return false;
    }
    
    /**
     * @param x the X coordinate of this event for the touching pointer
     * @param y the Y coordinate of this event for the touching pointer
     * @return Is touching the wheel
     */
    private final boolean isEventInWheel(float x, float y) {
        return false;
    }
    
    /**
     * If touch events are handled by ourselves, accept them all
     */
    @java.lang.Override()
    public boolean onTouchEvent(@org.jetbrains.annotations.NotNull()
    android.view.MotionEvent event) {
        return false;
    }
    
    /**
     * Calculate angle based on touch position
     *
     * @param xTouch
     * @param yTouch
     * @return Returns the minimum angle value represented by normal numbers, e.g., 45 degrees is 45, not 1/4×PI;
     * For obtuse angles like 315 degrees, returns -45;
     * Note: horizontal direction returns 0, vertical direction returns 90/-90
     */
    private final float getAngle(float xTouch, float yTouch) {
        return 0.0F;
    }
    
    /**
     * Calculate quadrant based on current position
     *
     * @param x
     * @param y
     * @return
     */
    private final int getQuadrant(float x, float y) {
        return 0;
    }
    
    public final void setAdapter(@org.jetbrains.annotations.Nullable()
    com.cursorwheel.CursorWheelView.CycleWheelAdapter adapter) {
    }
    
    private final void onDateSetChanged() {
    }
    
    /**
     * add menu item to this layout
     */
    private final void addMenuItems() {
    }
    
    /**
     * @param mPadding
     */
    public final void setPadding(float mPadding) {
    }
    
    private final int getDefaultWidth() {
        return 0;
    }
    
    private final void endFling(boolean scrollIntoSlots) {
    }
    
    /**
     * Scrolls the items so that the selected item is in its 'slot' (its center
     * is the Wheel's center).
     */
    private final void scrollIntoSlots(boolean showAnimation) {
    }
    
    /**
     * do some callback when selected position change
     */
    private final void selectionChangeCallback() {
    }
    
    /**
     * @param position
     */
    public final void setSelection(int position) {
    }
    
    /**
     * @param position
     */
    public final void setSelection(int position, boolean animation) {
    }
    
    public final void setSelectedAngle(double selectedAngle) {
    }
    
    @java.lang.Override()
    protected void onDetachedFromWindow() {
    }
    
    /**
     * to do whatever you want to perform the selected view
     *
     * @param v
     */
    protected void onInnerItemSelected(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    /**
     * to do whatever you want to perform the unselected view
     *
     * @param v
     */
    protected void onInnerItemUnselected(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u000b8\u0002X\u0083D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u000b8\u0002X\u0083D\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\u00020\u000b8\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u000bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/cursorwheel/CursorWheelView$Companion;", "", "<init>", "()V", "TAG", "", "RADIO_DEFAULT_CHILD_DIMENSION", "", "RADIO_DEFAULT_CENTER_DIMENSION", "RADIO_PADDING_LAYOUT", "INVALID_POSITION", "", "DEFAULT_SELECTED_ANGLE", "FLINGABLE_VALUE", "NOCLICK_VALUE", "DEFAULT_CURSOR_COLOR", "DEFAULT_WHEEL_BG_COLOR", "DEFAULT_GUIDE_LINE_COLOR", "getDEFAULT_GUIDE_LINE_COLOR", "()I", "DEFAULT_TRIANGLE_HEIGHT", "DEFAULT_GUIDE_LINE_WIDTH", "ITEM_ROTATE_MODE_NONE", "ITEM_ROTATE_MODE_INWARD", "ITEM_ROTATE_MODE_OUTWARD", "lib-view_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final int getDEFAULT_GUIDE_LINE_COLOR() {
            return 0;
        }
    }
    
    /**
     * An Adapter object acts as a bridge between an [CursorWheelView] and the
     * underlying data for that view. The Adapter provides access to the data items.
     * The Adapter is also responsible for making a [View] for
     * each item in the data set.
     *
     * @author chensuilun
     */
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\u000b\u001a\u00020\u0007J\b\u0010\f\u001a\u00020\rH&J\u001a\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\rH&J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0011\u001a\u00020\rH&R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/cursorwheel/CursorWheelView$CycleWheelAdapter;", "", "<init>", "()V", "mDataSetObservable", "Landroid/database/DataSetObservable;", "registerDataSetObserver", "", "observer", "Landroid/database/DataSetObserver;", "unregisterDataSetObserver", "notifyDataSetChanged", "getCount", "", "getView", "Landroid/view/View;", "parent", "position", "getItem", "lib-view_debug"})
    public static abstract class CycleWheelAdapter {
        @org.jetbrains.annotations.NotNull()
        private final android.database.DataSetObservable mDataSetObservable = null;
        
        public CycleWheelAdapter() {
            super();
        }
        
        public final void registerDataSetObserver(@org.jetbrains.annotations.NotNull()
        android.database.DataSetObserver observer) {
        }
        
        public final void unregisterDataSetObserver(@org.jetbrains.annotations.NotNull()
        android.database.DataSetObserver observer) {
        }
        
        /**
         * Notifies the attached observers that the underlying data has been changed
         * and any View reflecting the data set should refresh itself.
         */
        public final void notifyDataSetChanged() {
        }
        
        /**
         * How many menu items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        public abstract int getCount();
        
        /**
         * Get a View that displays the data at the specified position in the data set.
         *
         * @param parent
         * @param position
         * @return
         */
        @org.jetbrains.annotations.NotNull()
        public abstract android.view.View getView(@org.jetbrains.annotations.Nullable()
        android.view.View parent, int position);
        
        /**
         * Get the data item associated with the specified position in the data set.
         *
         * @param position Position of the item whose data we want within the adapter's
         * data set.
         * @return The data at the specified position.
         */
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.Object getItem(int position);
    }
    
    /**
     * Responsible for fling behavior.
     *
     * @author chensuilun
     */
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0007J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\tJ\b\u0010\u0015\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/cursorwheel/CursorWheelView$FlingRunnable;", "Ljava/lang/Runnable;", "<init>", "(Lcom/cursorwheel/CursorWheelView;)V", "mAngelPerSecond", "", "mStartUsingAngle", "", "mEndAngle", "", "mSweepAngle", "mBiggerBefore", "mInitStarAngle", "startCommon", "", "stop", "scrollIntoSlots", "startUsingVelocity", "velocity", "startUsingAngle", "angle", "run", "DEFAULT_REFRESH_TIME", "", "lib-view_debug"})
    final class FlingRunnable implements java.lang.Runnable {
        
        /**
         * Scrolling velocity
         */
        private float mAngelPerSecond = 0.0F;
        
        /**
         * Whether the purpose is to rotate to a specific angle
         */
        private boolean mStartUsingAngle = false;
        
        /**
         * Final angle
         */
        private double mEndAngle = 0.0;
        
        /**
         * Angle that needs to be rotated
         */
        private double mSweepAngle = 0.0;
        
        /**
         */
        private boolean mBiggerBefore = false;
        
        /**
         * Record the startAngle when rotation begins. Since [CursorWheelView.mStartAngle] is in [0,360)
         * it would be troublesome to directly compare it with [FlingRunnable.mEndAngle]
         */
        private double mInitStarAngle = 0.0;
        private final int DEFAULT_REFRESH_TIME = 16;
        
        public FlingRunnable() {
            super();
        }
        
        private final void startCommon() {
        }
        
        public final void stop(boolean scrollIntoSlots) {
        }
        
        /**
         * @param velocity
         */
        public final void startUsingVelocity(float velocity) {
        }
        
        /**
         * @param angle
         */
        public final void startUsingAngle(double angle) {
        }
        
        @java.lang.Override()
        public void run() {
        }
    }
    
    /**
     * @author chensuilun
     */
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/cursorwheel/CursorWheelView$InnerClickListener;", "Landroid/view/View$OnClickListener;", "mPosition", "", "<init>", "(Lcom/cursorwheel/CursorWheelView;I)V", "onClick", "", "v", "Landroid/view/View;", "lib-view_debug"})
    final class InnerClickListener implements android.view.View.OnClickListener {
        private final int mPosition = 0;
        
        public InnerClickListener(int mPosition) {
            super();
        }
        
        @java.lang.Override()
        public void onClick(@org.jetbrains.annotations.NotNull()
        android.view.View v) {
        }
    }
    
    /**
     * Interface definition for a callback to be invoked when a view is clicked.
     *
     * @author chensuilun
     */
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/cursorwheel/CursorWheelView$OnMenuItemClickListener;", "", "onItemClick", "", "view", "Landroid/view/View;", "pos", "", "lib-view_debug"})
    public static abstract interface OnMenuItemClickListener {
        
        public abstract void onItemClick(@org.jetbrains.annotations.Nullable()
        android.view.View view, int pos);
    }
    
    /**
     * callback when item selected
     *
     * @author chensuilun
     */
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Lcom/cursorwheel/CursorWheelView$OnMenuSelectedListener;", "", "onItemSelected", "", "parent", "Lcom/cursorwheel/CursorWheelView;", "view", "Landroid/view/View;", "pos", "", "lib-view_debug"})
    public static abstract interface OnMenuSelectedListener {
        
        public abstract void onItemSelected(@org.jetbrains.annotations.NotNull()
        com.cursorwheel.CursorWheelView parent, @org.jetbrains.annotations.Nullable()
        android.view.View view, int pos);
    }
    
    /**
     * @author chensuilun
     */
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\u0006"}, d2 = {"Lcom/cursorwheel/CursorWheelView$WheelDataSetObserver;", "Landroid/database/DataSetObserver;", "<init>", "(Lcom/cursorwheel/CursorWheelView;)V", "onChanged", "", "lib-view_debug"})
    public final class WheelDataSetObserver extends android.database.DataSetObserver {
        
        public WheelDataSetObserver() {
            super();
        }
        
        @java.lang.Override()
        public void onChanged() {
        }
    }
}