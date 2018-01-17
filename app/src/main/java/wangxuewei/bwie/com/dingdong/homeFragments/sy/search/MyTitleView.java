package wangxuewei.bwie.com.dingdong.homeFragments.sy.search;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import wangxuewei.bwie.com.dingdong.R;

/**
 * Created by jim on 2017/12/16.
 */

public class MyTitleView extends LinearLayout implements View.OnClickListener {

    private ImageView backSearch;
    private EditText editSearch;
    private Button btnSearch;

    public MyTitleView(Context context) {
        this(context, null);
    }

    public MyTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = View.inflate(context, R.layout.myview_title, this);
        backSearch = (ImageView) view.findViewById(R.id.sySearchBack);
        editSearch = (EditText) view.findViewById(R.id.sySearchEdit);
        btnSearch = (Button) view.findViewById(R.id.sySearchBtn);

        backSearch.setOnClickListener(this);
        editSearch.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;
            case R.id.sySearchBack:
                titleViewClickListener.titleViewBackClicked(v);
                break;
            case R.id.sySearchBtn:
                titleViewClickListener.titleViewButtonClicked(v);
                break;
            case R.id.sySearchEdit:
                titleViewClickListener.titleViewEditClicked(v);
                break;

        }
    }

    //定义接口的成员
    OnTitleViewClickListener titleViewClickListener;

    //接口的setter
    public void setOnTitleViewClickListenler(OnTitleViewClickListener titleViewClickListener) {
        //在setter中把这个接口的实现赋值给这个loginview的上面定义的接口
        this.titleViewClickListener = titleViewClickListener;
    }


    public interface OnTitleViewClickListener {

        void titleViewButtonClicked(View v);//传的参数

        void titleViewBackClicked(View v);//传的参数

        void titleViewEditClicked(View v);//传的参数

    }


}
