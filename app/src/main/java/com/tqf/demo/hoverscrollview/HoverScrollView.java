package com.tqf.demo.hoverscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author tangqianfeng
 */
public class HoverScrollView extends ScrollView {

    public static int WIDTH = 0;
    public static int HEIGHT = 0;

    boolean verticalMoveDown;
    boolean viewpagerScroll;
    float oldx;
    float oldy;
    OverScrollController mController;
    ScrollViewListener scrollViewListener;
    int stickyThreshold;

    public interface OverScrollController {
        public boolean canScrollUp();
    }

    public HoverScrollView(Context context) {
        super(context);
        init(context);
    }

    public HoverScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        initWidthAndHeight(context);
        stickyThreshold = (int) (18f / 1280f * WIDTH);
    }

    private void initWidthAndHeight(Context context) {
        if (context.getResources() != null) {
            if (context.getResources().getDisplayMetrics() != null) {
                WIDTH = this.getResources().getDisplayMetrics().widthPixels;
                HEIGHT = this.getResources().getDisplayMetrics().heightPixels;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldx = ev.getX();
                oldy = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                float currentX = ev.getX();
                float currentY = ev.getY();

                //子布局真正高度
                int scrollViewMeasuredHeight = getChildAt(0).getMeasuredHeight();
                //滑动距离
                int scrollY = getScrollY();
                //scrollview的高度
                int height = getHeight();

                //左右滑动 则scrollview不消费事件
                if (viewpagerScroll || (Math.abs(oldx - currentX) > stickyThreshold * 0.7 && (scrollY + height) >= scrollViewMeasuredHeight && currentY - oldy < stickyThreshold * 0.7)) {
                    viewpagerScroll = true;
                    requestDisallowInterceptTouchEvent(true);
                    return super.dispatchTouchEvent(ev);
                }

                if (oldy < currentY && currentY - oldy > stickyThreshold && Math.abs(oldx - currentX) < stickyThreshold * 1.2) {
                    verticalMoveDown = true;
                } else {
                    verticalMoveDown = false;
                }
                oldx = currentX;
                oldy = currentY;

                if ((scrollY + height) == scrollViewMeasuredHeight && verticalMoveDown == true) {
                    if (mController != null && mController.canScrollUp()) {
                        requestDisallowInterceptTouchEvent(false);
                    } else {
                        requestDisallowInterceptTouchEvent(true);
                    }
                } else if ((scrollY + height) >= scrollViewMeasuredHeight) {
                    requestDisallowInterceptTouchEvent(true);
                }

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                viewpagerScroll = false;
        }

        return super.dispatchTouchEvent(ev);
    }

    public void setController(OverScrollController controller) {
        mController = controller;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(x, y, oldx, oldy);
        }
    }

    public void setonScrollViewListener(ScrollViewListener listener) {
        this.scrollViewListener = listener;
    }

    public static interface ScrollViewListener {
        public void onScrollChanged(int x, int y, int oldx, int oldy);
    }

}
