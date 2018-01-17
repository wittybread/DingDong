package wangxuewei.bwie.com.dingdong.homeFragments.fl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.FlRightBean;
import wangxuewei.bwie.com.dingdong.homeFragments.fl.fladapter.FlLvAdapter;
import wangxuewei.bwie.com.dingdong.homeFragments.fl.fladapter.FlRightAdapter;

/**
 * Created by jim on 2017/12/8.
 */


public class FlFragment extends Fragment implements FlViewAPI {

    private ListView flLv;
    private RecyclerView flRightRectcler;
    private FlRightAdapter flRightAdapter;
    private FlLvAdapter flLvAdapter;
    private FlPresenter flPresenter;
    public static int sele = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fl, null);
        //加载控件
        initView(view);

        return view;
    }

    private void initData() {
        final List<String> tools = new ArrayList<String>();
        tools.add("京东超市");
        tools.add("手机数码");
        tools.add("女装");
        tools.add("女鞋");
        tools.add("箱包手袋");
        tools.add("钟表珠宝");
        tools.add("电脑办公");
        tools.add("食品生鲜");
        tools.add("母婴童装");
        tools.add("医药保健");
        tools.add("全球购");
        tools.add("男装");
        tools.add("男鞋");
        tools.add("内衣配饰");
        tools.add("美妆个护");
        tools.add("奢饰品");
        tools.add("家用电器");
        tools.add("酒水饮料");
        tools.add("玩具乐器");
        flLvAdapter = new FlLvAdapter(tools, getContext());
        flLv.setAdapter(flLvAdapter);
        flLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "tools" + position, Toast.LENGTH_SHORT).show();
                sele = position;
                flLvAdapter.notifyDataSetChanged();
                //点击切换
                reqData(position + 1);
            }
        });
    }




    //加载布局
    private void initView(View view) {
        flLv = (ListView) view.findViewById(R.id.flLv);
        flRightRectcler = (RecyclerView) view.findViewById(R.id.flRightRectcler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化数据
        initData();
        //请求数据
        reqData(1);
    }

    @Override
    public void onFlSuccess(FlRightBean flRightBean) {
        Log.d("FL", flRightBean.getData().size() + "View");
        //请求到的数据集合
        List<FlRightBean.DataBean> data = flRightBean.getData();
        flRightAdapter = new FlRightAdapter(data, getContext());
        flRightRectcler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        flRightRectcler.setAdapter(flRightAdapter);
        flRightAdapter.notifyDataSetChanged();


    }

    @Override
    public void onFlFailed(String err) {

    }

    public void reqData(int cid) {
        flPresenter = new FlPresenter(this, getContext());
        flPresenter.getFLRightData("http://120.27.23.105/product/getProductCatagory", cid);
    }


    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (flPresenter != null) {
            flPresenter.detach();
        }
    }


}
