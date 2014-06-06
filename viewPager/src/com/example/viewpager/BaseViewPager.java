package com.example.viewpager;


import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BaseViewPager extends ViewPager{

    PointF last;
    public DragImageView mCurrentView;
    public BaseViewPager(Context context) {
        super(context);
    }
    public BaseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private float[] handleMotionEvent(MotionEvent event)
    {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                last = new PointF(event.getX(0), event.getY(0));
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                PointF curr = new PointF(event.getX(0), event.getY(0));
                return new float[]{curr.x - last.x, curr.y - last.y};

        }
        return null;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP)
        {
            super.onTouchEvent(event);
        }	

        float [] difference = handleMotionEvent(event);
        if(mCurrentView == null){
        	return false;
        }
        if (mCurrentView.pagerCanScroll()) {
            return super.onTouchEvent(event);
        }
        else {
        	
            if (difference != null && mCurrentView.onRightSide && difference[0] < 0) //move right
            {
                return super.onTouchEvent(event);
            }
            if (difference != null && mCurrentView.onLeftSide && difference[0] > 0) //move left
            {
                return super.onTouchEvent(event);
            }
            if (difference == null && ( mCurrentView.onLeftSide || mCurrentView.onRightSide))
            {
                return super.onTouchEvent(event);
            }
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP)
        {
            super.onInterceptTouchEvent(event);
        }

        float [] difference = handleMotionEvent(event);
        if(mCurrentView == null){
        	return false;
        }

        if (mCurrentView.pagerCanScroll()) {
            return super.onInterceptTouchEvent(event);
        }
        else {
            if (difference != null && mCurrentView.onRightSide && difference[0] < 0) //move right
            {
                return super.onInterceptTouchEvent(event);
            }
            if (difference != null && mCurrentView.onLeftSide && difference[0] > 0) //move left
            {
                return super.onInterceptTouchEvent(event);
            }
            if (difference == null && ( mCurrentView.onLeftSide || mCurrentView.onRightSide))
            {
                return super.onInterceptTouchEvent(event);
            }
        }
        return false;
    }

	/*

	public BaseViewPager(Context context) {
		super(context);
	}
	
	public BaseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
	
	public static ViewPager mPager;//此处我直接在调用的时候静态赋值过来 的
    String TAG="@";
    public boolean dispatchTouchEvent(MotionEvent ev) {
            // TODO Auto-generated method stub       
            final float x = ev.getX();
            switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            	if(DragImageView.left !=0 && DragImageView.right != ConstDefinition.screenWidth){
            		super.requestDisallowInterceptTouchEvent(true);
            	}
                    break;
            case MotionEvent.ACTION_MOVE:
            	if(DragImageView.left !=0 && DragImageView.right != ConstDefinition.screenWidth){
            		super.requestDisallowInterceptTouchEvent(true);
            	}
                    break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            	ActImageShow.viewPager.requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
            // TODO Auto-generated method stub
            return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
            // TODO Auto-generated method stub
            return super.onTouchEvent(event);
    }

*/}
