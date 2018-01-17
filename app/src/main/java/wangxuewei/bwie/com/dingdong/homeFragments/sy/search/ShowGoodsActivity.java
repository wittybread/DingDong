package wangxuewei.bwie.com.dingdong.homeFragments.sy.search;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.GoodsListBean;
import wangxuewei.bwie.com.dingdong.homeFragments.sy.search.syadapter.GoodsListAdapter;

public class ShowGoodsActivity extends AppCompatActivity implements GoodsViewAPI, View.OnClickListener {

    private ImageView showBack;
    private ImageView cutLayout;
    private XRecyclerView xRecycle;
    private List<GoodsListBean.DataBean> list = new ArrayList<>();
    private GoodsListAdapter myAdapter;
    private int i = 1;
    private String key;
    private EditText searchEdit;
    private int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_goods);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        initView();

//        if (key.trim().equals("")) {
//            setListData(i, "手机");
//            key = "手机";
//        } else {
//            setListData(i, key2);
//            key = key2;
//        }
        String s = String.valueOf(i);
        setListData(s, key);
        searchEdit.setText(key);
        setConfig();

        xRecycle.setPullRefreshEnabled(true);
        xRecycle.setLoadingMoreEnabled(true);

        xRecycle.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecycle.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);

        clickListener();
    }

    //点击事件监听
    private void clickListener() {
        cutLayout.setOnClickListener(this);
        showBack.setOnClickListener(this);
    }

    //上拉刷新，下拉加载
    private void setConfig() {

        xRecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                i = 1;
                list.clear();
                setListData(i + "", key);
                xRecycle.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                i++;
                String s = String.valueOf(i);
                setListData(s, key);
                xRecycle.loadMoreComplete();
            }
        });
    }

    //找控件
    private void initView() {

        showBack = (ImageView) findViewById(R.id.showBack);
        cutLayout = (ImageView) findViewById(R.id.cutLayout);
        xRecycle = (XRecyclerView) findViewById(R.id.xRecycle);
        searchEdit = (EditText) findViewById(R.id.SearchEdit);

    }

    @Override
    public void onSuccess(GoodsListBean goodsListBean) {
        List<GoodsListBean.DataBean> data = goodsListBean.getData();
        list.addAll(data);
        setMyAdapter(flag);
    }

    @Override
    public void onFailed(String err) {
        Toast.makeText(this, "网络不好", Toast.LENGTH_SHORT).show();
    }


    public void setListData(String page, String keywords) {
        GoodsPresenter goodsPresenter = new GoodsPresenter(this, this);
        goodsPresenter.getGoodsListData("https://www.zhaoapi.cn/product/searchProducts", keywords, page);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cutLayout:
                if (flag == 1) {
                    cutLayout.setImageResource(R.drawable.b68);
                    flag = 2;
                } else {
                    cutLayout.setImageResource(R.drawable.b69);
                    flag = 1;
                }
                setMyAdapter(flag);
                break;
            case R.id.showBack:
                finish();
                break;

        }
    }


    public void setMyAdapter(int f) {
        if (f == 1) {
            // 线性布局管理器   VERTICAL默认样式/竖向显示       第三个参数是数据是否到过来显示
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            //添加布局管理器
            xRecycle.setLayoutManager(manager);
            myAdapter = new GoodsListAdapter(this, list, f);
            xRecycle.setAdapter(myAdapter);

        } else if (f == 2) {
            // 线性布局管理器   VERTICAL默认样式/竖向显示       第三个参数是数据是否到过来显示
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            //添加布局管理器
            xRecycle.setLayoutManager(gridLayoutManager);
            myAdapter = new GoodsListAdapter(this, list, f);
            xRecycle.setAdapter(myAdapter);
        }
    }
}
