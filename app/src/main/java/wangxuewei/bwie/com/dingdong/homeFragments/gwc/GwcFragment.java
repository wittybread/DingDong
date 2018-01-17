package wangxuewei.bwie.com.dingdong.homeFragments.gwc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.CartBean;
import wangxuewei.bwie.com.dingdong.homeFragments.gwc.Carlist.MyAdapter;
import wangxuewei.bwie.com.dingdong.homeFragments.gwc.entity.MessageEvent;
import wangxuewei.bwie.com.dingdong.homeFragments.gwc.entity.PriceAndCountEvent;
import wangxuewei.bwie.com.dingdong.homeFragments.gwc.order.OrderGoodsActivity;
import wangxuewei.bwie.com.dingdong.homeFragments.wd.login.LoginActivity;

/**
 * Created by jim on 2017/12/8.
 */


public class GwcFragment extends Fragment implements GwcViewAPI {

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
    private int price;
    private int count;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    //加载View
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_gwc, null);
        initView(view);
        return view;

    }


    private void initView(View view) {

        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        //未登录的购物车
        noLoginCar = (LinearLayout) view.findViewById(R.id.noLoginCar);
        //空的购物车
        emptyCar = (LinearLayout) view.findViewById(R.id.emptyCar);
        //有东西的购物车界面
        llcar = (LinearLayout) view.findViewById(R.id.llCar);
        //二级列表
        exListView = (ExpandableListView) view.findViewById(R.id.exListView);
        //全选
        all_chekbox = (CheckBox) view.findViewById(R.id.all_chekbox);
        //合计
        tv_total_price = (TextView) view.findViewById(R.id.tv_total_price);
        //去结算
        tv_go_to_pay = (TextView) view.findViewById(R.id.tv_go_to_pay);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //未登录，点击跳转到登录界面
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getContext().startActivity(intent);
            }
        });
        //登录成功后进行的操作  全选按钮
        all_chekbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.changeAllListCbState(all_chekbox.isChecked());
            }
        });
        //去结算按钮
        tv_go_to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    Toast.makeText(getContext(), "price" + price, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "price" + price, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), OrderGoodsActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }


            }
        });

    }

    //判断显示不同的界面
    public void shareP() {
        SharedPreferences sp = getContext().getSharedPreferences("loginMsg", Context.MODE_PRIVATE);
        loginSure = sp.getBoolean("loginSure", false);
        uid = sp.getInt("uid", -1);
        viewShow();
    }

    //刷新界面
    @Override
    public void onResume() {
        super.onResume();
        shareP();
    }

    //请求成功的数据
    @Override
    public void onGetCarDataSuccess(CartBean shoppingCar) {
        if (shoppingCar != null) {
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
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        all_chekbox.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        count = event.getCount();
        price = event.getPrice();
        //结算显示
        tv_go_to_pay.setText("结算(" + event.getCount() + ")");
        tv_total_price.setText("￥" + event.getPrice());
    }

    @Subscribe
    public void onMessageEvent(String flag) {
        Log.d("GWC", flag + "adapter");
        //结算显示
        if (flag.equals("0")) {
            emptyCar.setVisibility(View.VISIBLE);
            noLoginCar.setVisibility(View.GONE);
            llcar.setVisibility(View.GONE);
        }
    }


    //购物车为空
    @Override
    public void onGetNullCar(Boolean empty) {
        Log.d("GWC", empty + "FR");
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

    //网络原因，请求失败
    @Override
    public void onGetCarDataFailed(String s) {
        //请求失败，网络因素
    }

    //是否登录，登录请求显示，未登录，显示未登录
    public void viewShow() {
        if (loginSure) {
            groupList.clear();
            childList.clear();
            myAdapter = new MyAdapter(getContext(), groupList, childList, this, uid);
            gwcPresenter = new GwcPresenter(this, getContext());
            gwcPresenter.getCarData("http://120.27.23.105/product/getCarts", uid + "");
        } else {
            noLoginCar.setVisibility(View.VISIBLE);
            emptyCar.setVisibility(View.GONE);
            llcar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (gwcPresenter != null) {
            gwcPresenter.destroy();
        }
    }
}
