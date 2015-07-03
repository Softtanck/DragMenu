package com.softtanck.dragmenu.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * @Description : 侧滑菜单.
 * @Author : Tanck
 * @Date : 7/2/2015
 */

public class DragMenu extends FrameLayout {

    private float mSensittivity = 1.0f;//敏感度

    private ViewDragHelper dragHelper;
    private View mContentView;//主内容
    private View mMenuView;//菜单
    private int mContentWidth;//内容宽度
    private int mMenuWidth;//菜单高度
    private int isCanScrollPostion;//可以滚动的距离
    private int mScrollPositon;//当前滚动的位置
    private DragMenuStateEnum mDragState;//DragMenu状态
    private DragMenuStateEnum mDragLastState;// DragMenu最后的状态

    private OnDragMenuStateChangeListener listener;

    public enum DragMenuStateEnum {
        DRAG,
        OPEN,
        CLOSE
    }

    public interface OnDragMenuStateChangeListener {
        void OnDragOpen();

        void OnDragClose();

        void OnDrag(int positon);
    }

    public DragMenu(Context context) {
        this(context, null);
    }

    public DragMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    /**
     * 设置敏感度
     *
     * @param mSensittivity 敏感值
     */
    public void setmSensittivity(float mSensittivity) {
        if (0.0f > mSensittivity)
            mSensittivity = 0.0f;
        if (1.0f < mSensittivity)
            mSensittivity = 1.0f;
        this.mSensittivity = mSensittivity;
    }

    public void setOnDragMenuListener(OnDragMenuStateChangeListener listener) {
        this.listener = listener;
    }

    private void initView() {
        dragHelper = ViewDragHelper.create(this, mSensittivity, new DragCallBack());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (1 > getChildCount())
            throw new IllegalStateException("you need two ViewGroup.");
        if (!(getChildAt(0) instanceof ViewGroup) || !(getChildAt(1) instanceof ViewGroup)) {
            throw new IllegalStateException("the child not ViewGroup");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContentView = getChildAt(1);
        mMenuView = getChildAt(0);
        mContentWidth = mContentView.getWidth();
        mMenuWidth = mMenuView.getWidth();

        isCanScrollPostion = mContentWidth * 2 / 3;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            dragHelper.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private class DragCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View view, int i) {
            return true;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return isCanScrollPostion;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (changedView == mMenuView) {
                mMenuView.layout(0, 0, mMenuWidth, mMenuView.getHeight());
                mContentView.layout(left, 0, left + mContentWidth, mContentView.getHeight());
            }
            dispatchView(left);
            if (left != 0) {
                if (null != listener) {
                    listener.OnDrag(left);
                }
                mDragState = DragMenuStateEnum.DRAG;
                mScrollPositon = left;
            }
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
//            Log.d("Tanck","child:"+child);
            if (child == mContentView) {
                if (0 > left + dx)  // 左滑
                    return 0;
                else if (isCanScrollPostion >= left + dx)
                    return left;
                else
                    return isCanScrollPostion;
            } else
                return isCanScrollPostion;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (releasedChild == mContentView) {
                if (mScrollPositon < isCanScrollPostion / 2) {
                    //关闭
                    if (null != listener && mDragLastState.ordinal() != getmDragState()) {
                        listener.OnDragClose();
                    }
                    mDragState = DragMenuStateEnum.CLOSE;
                    mDragLastState = DragMenuStateEnum.CLOSE;
                    Close();
                } else {
                    //展开
                    if (null != listener && mDragLastState.ordinal() != getmDragState()) {
                        listener.OnDragOpen();
                    }
                    mDragState = DragMenuStateEnum.OPEN;
                    mDragLastState = DragMenuStateEnum.OPEN;
                    Open();
                }
            }
        }

        private void Open() {
            dragHelper.smoothSlideViewTo(mContentView, isCanScrollPostion, 0);
            ViewCompat.postInvalidateOnAnimation(DragMenu.this);
        }

        private void Close() {
            dragHelper.smoothSlideViewTo(mContentView, 0, 0);
            ViewCompat.postInvalidateOnAnimation(DragMenu.this);
        }
    }

    /**
     * 事件分开,用来处理视图特效
     *
     * @param left
     */
    private void dispatchView(int left) {
        float mRatio = left / (float) isCanScrollPostion;
        float mScale = 1 - mRatio * 0.3f;
        ViewHelper.setScaleX(mContentView, mScale);
        ViewHelper.setScaleY(mContentView, mScale);
        ViewHelper.setTranslationX(mMenuView, -mMenuWidth / 2.3f + mMenuWidth / 2.3f * mRatio);
        ViewHelper.setScaleX(mMenuView, 0.5f + 0.5f * mRatio);
        ViewHelper.setScaleY(mMenuView, 0.5f + 0.5f * mRatio);
        ViewHelper.setAlpha(mMenuView, mRatio);
    }

    /**
     * 获取Drag状态
     *
     * @return
     */
    public int getmDragState() {
        return mDragState.ordinal();
    }
}
