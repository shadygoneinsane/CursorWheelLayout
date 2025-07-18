package com.cursorwheel

import android.annotation.TargetApi
import android.content.Context
import android.database.DataSetObservable
import android.database.DataSetObserver
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Region
import android.os.Build
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.ColorInt
import com.cursorwheel.view.R // Added import for R
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.roundToInt
import kotlin.math.sin

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
open class CursorWheelView : ViewGroup {
    /**
     * CircleMenuLayout 's size
     */
    private var mRootDiameter = 0

    /**
     * Angle a touch can wander before we think the user is flinging
     */
    private val mFlingableValue = FLINGABLE_VALUE
    private var mPadding = 0f
    private var mStartAngle = 0.0

    /**
     * menu 's source data
     */
    private var mWheelAdapter: CycleWheelAdapter? = null

    /**
     *
     */
    private var mMenuItemCount = 0

    /**
     *
     */
    private var mTmpAngle = 0f

    /**
     *
     */
    private var mDownTime: Long = 0

    /**
     * weather is fling now
     */
    private var mIsFling = false

    /**
     *
     */
    private var mIsDraging = false

    /**
     *
     */
    private var mLastX = 0f
    private var mLastY = 0f

    /**
     * [0,360)
     */
    private var mSelectedAngle = DEFAULT_SELECTED_ANGLE.toDouble()

    /**
     * The currently selected item's child.
     */
    private var mSelectedView: View? = null

    /**
     * The position of the selected View
     */
    var selectedPosition = INVALID_POSITION
        private set

    /**
     * The temp selected item's child.
     */
    private var mTempSelectedView: View? = null

    /**
     * The position of the temp selected item's child.
     */
    private var mTempSelectedPosition = INVALID_POSITION

    /**
     * Determine whether scrolling to center is needed. Sometimes the selected angle
     * is the same as the layout center angle, so no additional movement is needed
     */
    private var mNeedSlotIntoCenter = false
    private val mFlingRunnable: FlingRunnable = FlingRunnable()

    /**
     * draw cursor
     */
    private var mCursorPaint: Paint? = null

    /**
     * draw wheel bg
     */
    private var mWheelPaint: Paint? = null

    /**
     * path of cursor
     */
    private var mTrianglePath: Path? = null
    private var mTriangleHeight = 0
    private var mGuideLineWidth = 0
    private var mGuideLineColor = 0

    /**
     * callback on menu item being click
     */
    private var mOnMenuItemClickListener: OnMenuItemClickListener? = null

    /**
     * callback on menu item being selected
     */
    private var mOnMenuSelectedListener: OnMenuSelectedListener? = null
    private var mIsFirstLayout = true

    @ColorInt
    private var mWheelBgColor = 0

    @ColorInt
    private var mCursorColor = 0
    private var mMenuRadioDimension = 0f
    private var mCenterRadioDimension = 0f
    private var mPaddingRadio = 0f
    private val mWheelBgPath = Path()
    private val mBgMatrix = Matrix()
    private val mBgRegion = Region()
    private val mGuidePath = Path()
    private var mGuidePaint: Paint? = null
    private var itemRotateMode = ITEM_ROTATE_MODE_NONE
    
    /**
     * Enable haptic feedback during scrolling
     */
    private var mHapticFeedbackEnabled = true

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context, attrs, defStyleAttr
    ) {
        initWheel(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context, attrs: AttributeSet?,
        defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initWheel(context, attrs)
    }

    private fun initWheel(context: Context, attrs: AttributeSet?) {
        setPadding(0, 0, 0, 0)
        val density = context.resources.displayMetrics.density
        mTriangleHeight = (DEFAULT_TRIANGLE_HEIGHT * density + 0.5).toInt()
        mGuideLineWidth = (DEFAULT_GUIDE_LINE_WIDTH * density + 0.5).toInt()
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.CursorWheelLayout)
            mSelectedAngle = ta.getFloat(
                R.styleable.CursorWheelLayout_wheelSelectedAngle,
                DEFAULT_SELECTED_ANGLE
            ).toDouble()
            if (mSelectedAngle > 360) {
                mSelectedAngle %= 360.0
            }
            mStartAngle = mSelectedAngle
            mWheelBgColor = ta.getColor(
                R.styleable.CursorWheelLayout_wheelBackgroundColor,
                DEFAULT_WHEEL_BG_COLOR
            )
            mCursorColor =
                ta.getColor(R.styleable.CursorWheelLayout_wheelCursorColor, DEFAULT_CURSOR_COLOR)
            mTriangleHeight = ta.getDimensionPixelOffset(
                R.styleable.CursorWheelLayout_wheelCursorHeight,
                mTriangleHeight
            )
            mMenuRadioDimension = ta.getFloat(
                R.styleable.CursorWheelLayout_wheelItemRadio,
                RADIO_DEFAULT_CHILD_DIMENSION
            )
            mCenterRadioDimension = ta.getFloat(
                R.styleable.CursorWheelLayout_wheelCenterRadio,
                RADIO_DEFAULT_CENTER_DIMENSION
            )
            mPaddingRadio =
                ta.getFloat(R.styleable.CursorWheelLayout_wheelPaddingRadio, RADIO_PADDING_LAYOUT)
            mGuideLineWidth = ta.getDimensionPixelOffset(
                R.styleable.CursorWheelLayout_wheelGuideLineWidth,
                mGuideLineWidth
            )
            mGuideLineColor = ta.getColor(
                R.styleable.CursorWheelLayout_wheelGuideLineColor,
                DEFAULT_GUIDE_LINE_COLOR
            )
            itemRotateMode =
                ta.getInt(R.styleable.CursorWheelLayout_wheelItemRotateMode, ITEM_ROTATE_MODE_NONE)
            ta.recycle()
        }
        init()
    }

    private fun init() {
        setWillNotDraw(false)
        mCursorPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mCursorPaint?.style = Paint.Style.FILL_AND_STROKE
        mCursorPaint?.color = mCursorColor
        mCursorPaint?.isDither = true
        mWheelPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mWheelPaint?.style = Paint.Style.FILL
        mWheelPaint?.color = mWheelBgColor
        mWheelPaint?.isDither = true
        mGuidePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mGuidePaint?.strokeWidth = mGuideLineWidth.toFloat()
        mGuidePaint?.color = mGuideLineColor
        mGuidePaint?.isDither = true
        mGuidePaint?.style = Paint.Style.STROKE
        mTrianglePath = Path()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = defaultWidth
        val widthSpec = resolveSizeAndState(desiredWidth, widthMeasureSpec)
        val heightSpec = resolveSizeAndState(desiredWidth, heightMeasureSpec)
        setMeasuredDimension(widthSpec, heightSpec)
        mRootDiameter = measuredWidth.coerceAtLeast(measuredHeight)
        val count = childCount
        // menu item 's size
        val childSize = (mRootDiameter * mMenuRadioDimension).toInt()
        // menu item 's MeasureSpec
        val childMode = MeasureSpec.EXACTLY
        for (i in 0 until count) {
            val child = getChildAt(i)
            if (child.visibility == View.GONE) {
                continue
            }
            val makeMeasureSpec: Int = if (child.id == R.id.id_wheel_menu_center_item) {
                MeasureSpec.makeMeasureSpec(
                    (mRootDiameter * mCenterRadioDimension).toInt(),
                    childMode
                )
            } else {
                MeasureSpec.makeMeasureSpec(
                    childSize,
                    childMode
                )
            }
            child.measure(makeMeasureSpec, makeMeasureSpec)
        }
        mPadding = mPaddingRadio * mRootDiameter
    }

    private fun resolveSizeAndState(desireSize: Int, measureSpec: Int): Int {
        var result = desireSize
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        when (specMode) {
            MeasureSpec.UNSPECIFIED -> result = desireSize
            MeasureSpec.AT_MOST -> result = Math.min(specSize, desireSize)
            MeasureSpec.EXACTLY -> result = specSize
        }
        return result
    }

    val centerItem: View?
        get() = findViewById(R.id.id_wheel_menu_center_item)

    /**
     * init the triangle path
     */
    private fun initTriangle() {
        val layoutRadial = (mRootDiameter / 2.0).toInt()
        mTrianglePath?.moveTo(layoutRadial - mTriangleHeight.toFloat(), 0f)
        mTrianglePath?.lineTo(layoutRadial.toFloat(), 0 - mTriangleHeight / 2.0f)
        mTrianglePath?.lineTo(layoutRadial.toFloat(), 0 + mTriangleHeight / 2.0f)
        mTrianglePath?.close()
    }

    /**
     * @param mOnMenuItemClickListener
     */
    fun setOnMenuItemClickListener(
        mOnMenuItemClickListener: OnMenuItemClickListener?
    ) {
        this.mOnMenuItemClickListener = mOnMenuItemClickListener
    }

    fun setOnMenuSelectedListener(onMenuSelectedListener: OnMenuSelectedListener) {
        mOnMenuSelectedListener = onMenuSelectedListener
    }
    
    /**
     * Enable haptic feedback during scrolling
     */
    fun setWheelHapticFeedbackEnabled(enabled: Boolean) {
        mHapticFeedbackEnabled = enabled
    }
    
    /**
     * @return true if haptic feedback is enabled
     */
    fun isWheelHapticFeedbackEnabled(): Boolean {
        return mHapticFeedbackEnabled
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val layoutDiameter = mRootDiameter
        val layoutRadial = (layoutDiameter / 2.0).toInt()
        val childCount = childCount
        // size of menu item
        val cWidth = (layoutDiameter * mMenuRadioDimension).toInt()
        val angleDelay: Float = if (centerItem != null) {
            360 / (getChildCount() - 1).toFloat()
        } else {
            360 / getChildCount().toFloat()
        }
        //angle diff [0,360)
        var minimumAngleDiff = -1.0
        var angleDiff: Double
        var includedAngle: Double
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.id == R.id.id_wheel_menu_center_item) {
                continue
            }
            if (child.visibility == GONE) {
                continue
            }
            mStartAngle %= 360.0
            includedAngle = mStartAngle
            //            }
            //menu 's angle relative to 0°
            child.setTag(R.id.id_wheel_view_angle, mStartAngle)
            angleDiff = abs(mSelectedAngle - includedAngle)
            angleDiff = if (angleDiff >= 180) 360 - angleDiff else angleDiff
            //find the intentional selected item
            if (minimumAngleDiff == -1.0 || minimumAngleDiff > angleDiff) {
                minimumAngleDiff = angleDiff
                mTempSelectedView = child
                mTempSelectedPosition = if (centerItem != null) {
                    i - 1
                } else {
                    i
                }
                //allowable error
                mNeedSlotIntoCenter = minimumAngleDiff.toInt() != 0
            }
            // Calculation, center point to menu item distance from center
            val tmp = layoutRadial - cWidth / 2 - mPadding

            // {tmp*cos(a)-1/2*width} which is menu item, the abscissa relative to the center
            val sRelativeCenter = (tmp * cos(Math.toRadians(mStartAngle)) - 1 / 2f * cWidth)
            val left = if (!sRelativeCenter.isNaN())
                layoutRadial + sRelativeCenter.roundToInt()
            else layoutRadial

            //{tmp*sin(a)-1/2*height} which is menu item, the ordinate relative to the center point
            val oRelativeCenter = (tmp * sin(Math.toRadians(mStartAngle)) - 1 / 2f * cWidth)
            val top = if (!oRelativeCenter.isNaN())
                layoutRadial + oRelativeCenter.roundToInt()
            else layoutRadial

            child.layout(left, top, left + cWidth, top + cWidth)
            val angel: Float = when (itemRotateMode) {
                ITEM_ROTATE_MODE_NONE -> 0f
                ITEM_ROTATE_MODE_INWARD -> (-90 + mStartAngle).toFloat()
                ITEM_ROTATE_MODE_OUTWARD -> (90 + mStartAngle).toFloat()
                else -> 0f
            }
            child.pivotX = cWidth / 2.0f
            child.pivotY = cWidth / 2.0f
            child.rotation = angel
            mStartAngle += angleDelay.toDouble()
        }
        //layout center menu
        val cView = findViewById<View>(R.id.id_wheel_menu_center_item)
        if (cView != null) {
            // Set center item position
            val cl = layoutRadial - cView.measuredWidth / 2
            val cr = cl + cView.measuredWidth
            cView.layout(cl, cl, cr, cr)
        }
        if (mIsFirstLayout) {
            mIsFirstLayout = false
            scrollIntoSlots()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBgMatrix.reset()
        initTriangle()
        val radial = (mRootDiameter / 2.0f).toInt()
        mWheelBgPath.addCircle(0f, 0f, radial.toFloat(), Path.Direction.CW)
    }

    override fun requestLayout() {
        mIsFirstLayout = true
        super.requestLayout()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //Draw Wheel's Background
        canvas.save()
        val radial = (mRootDiameter / 2.0f).toInt()
        canvas.translate(radial.toFloat(), radial.toFloat())
        if (mBgMatrix.isIdentity) {
            canvas.matrix.invert(mBgMatrix)
        }
        canvas.drawPath(mWheelBgPath, mWheelPaint!!)
        canvas.restore()
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        canvas.save()
        canvas.translate(mRootDiameter / 2f, mRootDiameter / 2f)
        canvas.rotate(mSelectedAngle.toFloat(), 0f, 0f)
        canvas.drawPath(mTrianglePath!!, mCursorPaint!!)
        canvas.restore()
        val angleDelay: Float
        val startIndex: Int
        if (centerItem != null) {
            angleDelay = 360 / (childCount - 1).toFloat()
            startIndex = 1
        } else {
            angleDelay = 360 / childCount.toFloat()
            startIndex = 0
        }
        if (mGuideLineWidth > 0 && childCount - startIndex == mMenuItemCount) {
            canvas.save()
            canvas.translate(mRootDiameter / 2f, mRootDiameter / 2f)
            val child = getChildAt(startIndex)
            if (child?.getTag(R.id.id_wheel_view_angle) != null) {
                var startAngel =
                    ((child.getTag(R.id.id_wheel_view_angle) as Double + angleDelay / 2f) % 360).toInt()
                for (i in startIndex until childCount) {
                    canvas.save()
                    canvas.rotate(startAngel.toFloat())
                    mGuidePath.reset()
                    mGuidePath.moveTo(0f, 0f)
                    mGuidePath.lineTo(mRootDiameter / 2f, 0f)
                    canvas.drawPath(mGuidePath, mGuidePaint!!)
                    startAngel += angleDelay.toInt()
                    canvas.restore()
                }
            }
            canvas.restore()
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!isEventInWheel(x, y)) {
                    return false
                }
                mLastX = x
                mLastY = y
                mDownTime = System.currentTimeMillis()
                mTmpAngle = 0f
                mIsDraging = false
                if (mIsFling) {
                    removeCallbacks(mFlingRunnable)
                    mIsFling = false
                    return true
                }
            }

            MotionEvent.ACTION_MOVE -> {
                /**
                 * Get the starting angle
                 */
                val start = getAngle(mLastX, mLastY)

                /**
                 * Get the current angle
                 */
                val end = getAngle(x, y)

                // If in quadrants 1 or 4, (end-start) directly represents angle change (positive = up, negative = down)
                if (getQuadrant(x, y) == 1 || getQuadrant(x, y) == 4) {
                    mStartAngle += end - start.toDouble()
                    mTmpAngle += end - start
                } else  // For quadrants 2 and 3, (start - end) represents angle change
                {
                    mStartAngle += start - end.toDouble()
                    mTmpAngle += start - end
                }
                if (!mIsDraging && mHapticFeedbackEnabled) {
                    // Provide subtle haptic feedback when dragging starts
                    performHapticFeedback(
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            HapticFeedbackConstants.CONTEXT_CLICK
                        } else {
                            HapticFeedbackConstants.VIRTUAL_KEY
                        }
                    )
                }
                mIsDraging = true
                // Re-layout
                requestLayout()
                mLastX = x
                mLastY = y
            }

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                // Calculate angle moved per second
                val anglePerSecond = (mTmpAngle * 1000
                        / (System.currentTimeMillis() - mDownTime))
                // If this value is reached, consider it fast movement
                if (Math.abs(anglePerSecond) > mFlingableValue && !mIsFling) {
                    mFlingRunnable.startUsingVelocity(anglePerSecond)
                    return true
                }
                mIsFling = false
                mIsDraging = false
                mFlingRunnable.stop(false)
                scrollIntoSlots()
                // If current rotation angle exceeds NOCLICK_VALUE, block clicks
                if (Math.abs(mTmpAngle) > NOCLICK_VALUE) {
                    return true
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    /**
     * @param x the X coordinate of this event for the touching pointer
     * @param y the Y coordinate of this event for the touching pointer
     * @return Is touching the wheel
     */
    private fun isEventInWheel(x: Float, y: Float): Boolean {
        val pts = FloatArray(2)
        pts[0] = x
        pts[1] = y
        mBgMatrix.mapPoints(pts)
        val bounds = RectF()
        mWheelBgPath.computeBounds(bounds, true)
        mBgRegion.setPath(
            mWheelBgPath,
            Region(
                bounds.left.toInt(),
                bounds.top.toInt(),
                bounds.right.toInt(),
                bounds.bottom.toInt()
            )
        )
        return mBgRegion.contains(pts[0].toInt(), pts[1].toInt())
    }

    /**
     * If touch events are handled by ourselves, accept them all
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return true
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
    private fun getAngle(xTouch: Float, yTouch: Float): Float {
        val x = xTouch - mRootDiameter / 2.0
        val y = yTouch - mRootDiameter / 2.0
        return (asin(y / hypot(x, y)) * 180 / Math.PI).toFloat()
    }

    /**
     * Calculate quadrant based on current position
     *
     * @param x
     * @param y
     * @return
     */
    private fun getQuadrant(x: Float, y: Float): Int {
        val tmpX = (x - mRootDiameter / 2).toInt()
        val tmpY = (y - mRootDiameter / 2).toInt()
        return if (tmpX >= 0) {
            if (tmpY >= 0) 4 else 1
        } else {
            if (tmpY >= 0) 3 else 2
        }
    }

    fun setAdapter(adapter: CycleWheelAdapter?) {
        requireNotNull(adapter) { "Can not set a null adapter to CursorWheelLayout!!!" }
        if (mWheelAdapter != null) {
            if (mWheelDataSetObserver != null) {
                mWheelAdapter?.unregisterDataSetObserver(mWheelDataSetObserver!!)
            }
            removeAllViews()
            mWheelDataSetObserver = null
        }
        mWheelAdapter = adapter
        mWheelDataSetObserver = WheelDataSetObserver()
        mWheelAdapter?.registerDataSetObserver(mWheelDataSetObserver!!)
        addMenuItems()
    }

    private fun onDateSetChanged() {
        mFlingRunnable.stop(false)
        removeAllViews()
        addMenuItems()
        mStartAngle = mSelectedAngle
        selectedPosition = INVALID_POSITION
        mTempSelectedPosition = INVALID_POSITION
        mSelectedView = null
        mTempSelectedView = null
        mIsDraging = false
        mIsFling = false
        requestLayout()
    }

    /**
     * add menu item to this layout
     */
    private fun addMenuItems() {
        require(!(mWheelAdapter == null || mWheelAdapter!!.getCount() == 0)) { "Empty menu source!" }
        mMenuItemCount = mWheelAdapter!!.getCount()
        var view: View
        for (i in 0 until mMenuItemCount) {
            view = mWheelAdapter!!.getView(this, i)
            //it will be ignore the origin onClickListener
            view.setOnClickListener(InnerClickListener(i))
            addView(view)
        }
    }

    /**
     * @param mPadding
     */
    fun setPadding(mPadding: Float) {
        this.mPadding = mPadding
        invalidate()
    }

    /**
     * @return
     */
    private val defaultWidth: Int
        get() {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.widthPixels.coerceAtMost(outMetrics.heightPixels)
        }

    private fun endFling(scrollIntoSlots: Boolean) {
        mIsDraging = false
        mIsFling = false
        if (scrollIntoSlots) {
            scrollIntoSlots()
        }
    }

    /**
     * Scrolls the items so that the selected item is in its 'slot' (its center
     * is the Wheel's center).
     */
    private fun scrollIntoSlots(showAnimation: Boolean = true) {
        if (mIsDraging || mIsFling) {
            return
        }
        if (childCount == 0 || mTempSelectedView == null) {
            return
        }
        if (!mNeedSlotIntoCenter) {
            if (mSelectedView !== mTempSelectedView || selectedPosition != mTempSelectedPosition) {
                onInnerItemUnselected(mSelectedView)
                mSelectedView = mTempSelectedView
                onInnerItemSelected(mSelectedView)
                selectedPosition = mTempSelectedPosition
                mTempSelectedView = null
                mTempSelectedPosition = INVALID_POSITION
                selectionChangeCallback()
                
                // Provide haptic feedback when item selection changes
                if (mHapticFeedbackEnabled) {
                    performHapticFeedback(
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            HapticFeedbackConstants.CONTEXT_CLICK
                        } else {
                            HapticFeedbackConstants.VIRTUAL_KEY
                        }
                    )
                }
            }
        } else {
            val angle: Double = try {
                mTempSelectedView?.getTag(R.id.id_wheel_view_angle) as Double
            } catch (e: NullPointerException) {
                return
            }
            if (angle > 360) {
                Log.w(
                    TAG,
                    "scrollIntoSlots:$angle > 360, may be something wrong with calculate angle onLayout"
                )
            }
            var diff = Math.abs(mSelectedAngle - angle)
            diff = if (diff >= 180) 360 - diff else diff
            val diagonal = (angle + 180) % 360
            val clockWise: Boolean
            clockWise = if (diagonal < angle) {
                mSelectedAngle <= diagonal || mSelectedAngle >= angle
            } else {
                mSelectedAngle in angle..diagonal
            }
            val sweepAngle = diff * if (clockWise) 1 else -1
            if (showAnimation) {
                mFlingRunnable.stop(false)
                mFlingRunnable.startUsingAngle(sweepAngle)
            } else {
                mStartAngle += sweepAngle
                requestLayout()
            }
        }
    }

    /**
     * do some callback when selected position change
     */
    private fun selectionChangeCallback() {
        if (mOnMenuSelectedListener != null) {
            mOnMenuSelectedListener?.onItemSelected(this, mSelectedView, selectedPosition)
        }
    }

    /**
     * Responsible for fling behavior.
     *
     * @author chensuilun
     */
    private inner class FlingRunnable : Runnable {
        /**
         * Scrolling velocity
         */
        private var mAngelPerSecond = 0f

        /**
         * Whether the purpose is to rotate to a specific angle
         */
        private var mStartUsingAngle = false

        /**
         * Final angle
         */
        private var mEndAngle = 0.0

        /**
         * Angle that needs to be rotated
         */
        private var mSweepAngle = 0.0

        /**
         *
         */
        private var mBiggerBefore = false

        /**
         * Record the startAngle when rotation begins. Since [CursorWheelView.mStartAngle] is in [0,360)
         * it would be troublesome to directly compare it with [FlingRunnable.mEndAngle]
         */
        private var mInitStarAngle = 0.0
        private fun startCommon() {
            // Remove any pending flings
            removeCallbacks(this)
        }

        fun stop(scrollIntoSlots: Boolean) {
            removeCallbacks(this)
            endFling(scrollIntoSlots)
        }

        /**
         * @param velocity
         */
        fun startUsingVelocity(velocity: Float) {
            mStartUsingAngle = false
            startCommon()
            mAngelPerSecond = velocity
            post(this)
        }

        /**
         * @param angle
         */
        fun startUsingAngle(angle: Double) {
            mStartUsingAngle = true
            mSweepAngle = angle
            mInitStarAngle = mStartAngle
            mEndAngle = mSweepAngle + mInitStarAngle
            mBiggerBefore = mInitStarAngle >= mEndAngle
            post(this)
        }

        override fun run() {
            if (mMenuItemCount == 0) {
                stop(true)
                return
            }
            if (!mStartUsingAngle) {
                if (abs(mAngelPerSecond) < 20) {
                    stop(true)
                    return
                }
                mIsFling = true
                mStartAngle += mAngelPerSecond / 30
                mAngelPerSecond /= 1.0666f
            } else {
                mStartAngle %= 360.0
                if (abs((mEndAngle - mInitStarAngle).toInt()) == 0 || mBiggerBefore && mInitStarAngle < mEndAngle || !mBiggerBefore && mInitStarAngle > mEndAngle) {
                    mNeedSlotIntoCenter = false
                    stop(true)
                    return
                }
                mIsFling = true
                val change = mSweepAngle / 5
                mInitStarAngle += change
                mStartAngle += change
            }
            postDelayed(this, DEFAULT_REFRESH_TIME.toLong())
            requestLayout()
        }

        private val DEFAULT_REFRESH_TIME = 16
    }

    /**
     * Interface definition for a callback to be invoked when a view is clicked.
     *
     * @author chensuilun
     */
    interface OnMenuItemClickListener {
        fun onItemClick(view: View?, pos: Int)
    }

    /**
     * @author chensuilun
     */
    private inner class InnerClickListener(private val mPosition: Int) : OnClickListener {
        override fun onClick(v: View) {
            if (mSelectedView === v || mTempSelectedView === v) {
                return
            }
            mFlingRunnable.stop(false)
            mIsDraging = false
            mIsFling = false
            mTempSelectedPosition = mPosition
            mTempSelectedView = v
            mNeedSlotIntoCenter = true
            scrollIntoSlots()
            if (mOnMenuItemClickListener != null) {
                mOnMenuItemClickListener?.onItemClick(v, mPosition)
            }
        }
    }

    /**
     * @param position
     */
    fun setSelection(position: Int) {
        setSelection(position, true)
    }

    /**
     * @param position
     */
    fun setSelection(position: Int, animation: Boolean) {
        require(position <= mMenuItemCount) { "Position:$position is out of index!" }
        post {
            val itemPosition = if (centerItem == null) position else position + 1
            mFlingRunnable.stop(false)
            mIsDraging = false
            mIsFling = false
            mTempSelectedPosition = itemPosition
            mTempSelectedView = getChildAt(itemPosition)
            mNeedSlotIntoCenter = true
            scrollIntoSlots(animation)
        }
    }

    fun setSelectedAngle(selectedAngle: Double) {
        var selectedAngle = selectedAngle
        if (selectedAngle < 0) {
            return
        }
        if (selectedAngle > 360) {
            selectedAngle %= 360.0
        }
        mSelectedAngle = selectedAngle
        requestLayout()
    }

    override fun onDetachedFromWindow() {
        //removeAllViews
        super.onDetachedFromWindow()
        mFlingRunnable.stop(false)
        mIsFirstLayout = false
    }

    /**
     * to do whatever you want to perform the selected view
     *
     * @param v
     */
    protected open fun onInnerItemSelected(v: View?) {
        //No implementation
    }

    /**
     * to do whatever you want to perform the unselected view
     *
     * @param v
     */
    protected open fun onInnerItemUnselected(v: View?) {}

    /**
     * callback when item selected
     *
     * @author chensuilun
     */
    interface OnMenuSelectedListener {
        fun onItemSelected(parent: CursorWheelView, view: View?, pos: Int)
    }

    private var mWheelDataSetObserver: WheelDataSetObserver? = null

    /**
     * @author chensuilun
     */
    inner class WheelDataSetObserver : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            onDateSetChanged()
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
    abstract class CycleWheelAdapter {
        private val mDataSetObservable = DataSetObservable()
        fun registerDataSetObserver(observer: DataSetObserver) {
            mDataSetObservable.registerObserver(observer)
        }

        fun unregisterDataSetObserver(observer: DataSetObserver) {
            mDataSetObservable.unregisterObserver(observer)
        }

        /**
         * Notifies the attached observers that the underlying data has been changed
         * and any View reflecting the data set should refresh itself.
         */
        fun notifyDataSetChanged() {
            mDataSetObservable.notifyChanged()
        }

        /**
         * How many menu items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        abstract fun getCount(): Int

        /**
         * Get a View that displays the data at the specified position in the data set.
         *
         * @param parent
         * @param position
         * @return
         */
        abstract fun getView(parent: View?, position: Int): View

        /**
         * Get the data item associated with the specified position in the data set.
         *
         * @param position Position of the item whose data we want within the adapter's
         * data set.
         * @return The data at the specified position.
         */
        abstract fun getItem(position: Int): Any?
    }

    companion object {
        private const val TAG = "CircleMenuLayout"

        /**
         * size of menu item relative to parent
         */
        private const val RADIO_DEFAULT_CHILD_DIMENSION = 1 / 4f

        /**
         * size of center item relative to parent
         */
        private const val RADIO_DEFAULT_CENTER_DIMENSION = 1 / 3f

        /**
         * ignore the origin padding ,real padding size determine by parent size
         */
        private const val RADIO_PADDING_LAYOUT = 1 / 12f
        private const val INVALID_POSITION = -1
        private const val DEFAULT_SELECTED_ANGLE = 0f

        /**
         * Angle a touch can wander before we think the user is flinging
         */
        private const val FLINGABLE_VALUE = 300

        /**
         *
         */
        private const val NOCLICK_VALUE = 3

        /**
         * default cursor color
         */
        @ColorInt
        private val DEFAULT_CURSOR_COLOR = -0x3ad6

        /**
         * default wheel background color
         */
        @ColorInt
        private val DEFAULT_WHEEL_BG_COLOR = -0x1aece8e4

        @ColorInt
        val DEFAULT_GUIDE_LINE_COLOR = -0x8d8d8e

        //DP
        const val DEFAULT_TRIANGLE_HEIGHT = 13

        //DP
        const val DEFAULT_GUIDE_LINE_WIDTH = 0

        /**
         * Don't rotate my item.DEFAULT
         */
        const val ITEM_ROTATE_MODE_NONE = 0
        const val ITEM_ROTATE_MODE_INWARD = 1
        const val ITEM_ROTATE_MODE_OUTWARD = 2
    }
}