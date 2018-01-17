package wangxuewei.bwie.com.dingdong.homeFragments.sy.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import wangxuewei.bwie.com.dingdong.R;

/**
 * Created by jim on 2017/12/17.
 */

public class MyFlowLayout extends ViewGroup {


    public MyFlowLayout(Context context) {
        this(context, null);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchActivity);
        a.getColor(R.styleable.SearchActivity_textColor, 0XFFFFFFFF);
        float textSize = a.getDimension(R.styleable.SearchActivity_textSize, 36);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int tw = 0;
        int th = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (tw + child.getWidth() < width) {
            } else {
                tw = 0;
                th += child.getMeasuredHeight();
            }
            child.layout(tw, th, tw + child.getMeasuredWidth(), th + child.getMeasuredHeight());
            tw += child.getMeasuredWidth();
        }

    }

    public void btn_re(View view) {

        Toast.makeText(getContext(), getChildAt(1) + "1234", Toast.LENGTH_SHORT).show();
    }
}
