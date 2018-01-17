package wangxuewei.bwie.com.demo02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import wangxuewei.bwie.com.demo02.bean.CartBean;
import wangxuewei.bwie.com.demo02.entity.MessageEvent;
import wangxuewei.bwie.com.demo02.entity.PriceAndCountEvent;

public class MainActivity extends AppCompatActivity implements GwcViewAPI {
    private boolean loginSure;
    private int uid;
    private View view;
    private Button btnLogin;
    private LinearLayout noLoginCar;
    private LinearLayout emptyCar;
    private LinearLayout llcar;
    private boolean flag;
    private ExpandableListView exListView;
    private CheckBox all_chekbox;
    private TextView tv_total_price;
    private TextView tv_go_to_pay;
    private List<CartBean.DataBean> groupList = new ArrayList<>();
    private List<List<CartBean.DataBean.ListBean>> childList = new ArrayList<>();
    private MyAdapter myAdapter;
    private GwcPresenter gwcPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 只注册一次，在创建的时候注册
         */
        EventBus.getDefault().register(this);

        initView();

        gwcPresenter = new GwcPresenter(this, MainActivity.this);
        gwcPresenter.getCarData("http://120.27.23.105/product/getCarts", 3471 + "");

        myAdapter = new MyAdapter(this, groupList, childList);

        //登录成功后进行的操作
        all_chekbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.changeAllListCbState(all_chekbox.isChecked());
            }
        });

    }

    private void initView() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        //未登录的购物车
        noLoginCar = (LinearLayout) findViewById(R.id.noLoginCar);
        //空的购物车
        emptyCar = (LinearLayout) findViewById(R.id.emptyCar);
        //有东西的购物车界面
        llcar = (LinearLayout) findViewById(R.id.llCar);
        //二级列表
        exListView = (ExpandableListView) findViewById(R.id.exListView);
        //全选
        all_chekbox = (CheckBox) findViewById(R.id.all_chekbox);
        //合计
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        //去结算
        tv_go_to_pay = (TextView) findViewById(R.id.tv_go_to_pay);
    }

    public void onMessageEvent(MessageEvent event) {
        all_chekbox.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        //结算显示
        tv_go_to_pay.setText("结算(" + event.getCount() + ")");
        tv_total_price.setText("￥" + event.getPrice());
    }

    @Override
    public void onGetCarDataSuccess(CartBean shoppingCar) {
        List<CartBean.DataBean> data = shoppingCar.getData();
        groupList.addAll(data);
        for (int i = 0; i < data.size(); i++) {
            List<CartBean.DataBean.ListBean> datas = data.get(i).getList();
            childList.add(datas);
        }
        exListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        exListView.setGroupIndicator(null);
        //一级列表固定展开
        for (int i = 0; i < groupList.size(); i++) {
            exListView.expandGroup(i);
        }
    }

    @Override
    public void onGetNullCar(Boolean empty) {
        if (empty) {
            emptyCar.setVisibility(View.VISIBLE);
            noLoginCar.setVisibility(View.GONE);
            llcar.setVisibility(View.GONE);
        } else {
            emptyCar.setVisibility(View.GONE);
            noLoginCar.setVisibility(View.GONE);
            llcar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetCarDataFailed(String s) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (gwcPresenter != null) {
            gwcPresenter.detatch();
        }
    }
}
