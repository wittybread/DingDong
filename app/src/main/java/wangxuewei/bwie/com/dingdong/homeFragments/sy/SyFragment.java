package wangxuewei.bwie.com.dingdong.homeFragments.sy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.WebViewActivity;
import wangxuewei.bwie.com.dingdong.bean.SyBean;
import wangxuewei.bwie.com.dingdong.bean.SyflBean;
import wangxuewei.bwie.com.dingdong.homeFragments.sy.kb.NoticeView;
import wangxuewei.bwie.com.dingdong.homeFragments.sy.search.SySearchActivity;
import wangxuewei.bwie.com.dingdong.homeFragments.sy.syadapter.FlAdapter;
import wangxuewei.bwie.com.dingdong.homeFragments.sy.syadapter.SyTjAdapter;

/**
 * Created by jim on 2017/12/8.
 */


public class SyFragment extends Fragment implements SyViewAPI, View.OnClickListener {

    private Banner syBanner;
    private SyPresenter syPresenter;
    private List<String> bannerList = new ArrayList<>();
    private final int REQUEST_CODE = 258;
    private RecyclerView flRecyclerView;
    private RecyclerView syRecyclerView;
    private TextView syTitle;
    private LinearLayout syQrcode;
    private EditText sySearch;
    private NoticeView noticeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sy, null);
        //加载控件
        initView(view);
        //点击事件
        clickListener();

        return view;
    }

    private void clickListener() {
        syQrcode.setOnClickListener(this);
        sySearch.setOnClickListener(this);
    }


    //加载控件
    private void initView(View view) {
        //banner
        syBanner = (Banner) view.findViewById(R.id.syBanner);
        //banner下的分类
        flRecyclerView = (RecyclerView) view.findViewById(R.id.flRecyclerView);
        //为你推荐
        syRecyclerView = (RecyclerView) view.findViewById(R.id.syRecyclerView);
        //title---------为你推荐
        syTitle = (TextView) view.findViewById(R.id.SyTitle);
        //扫一扫
        syQrcode = (LinearLayout) view.findViewById(R.id.syQRcode);
        //搜索
        sySearch = (EditText) view.findViewById(R.id.sySearch);
        //快报
        noticeView = (NoticeView) view.findViewById(R.id.notice_view);


    }


    //配置banner
    private void setBanner(final List<SyBean.DataBean> l) {
        for (int i = 0; i < l.size(); ++i) {
            bannerList.add(l.get(i).getIcon());
        }
        syBanner.setImageLoader(new MyImageLoader());
        //设置图片集合
        syBanner.setImages(bannerList);
        //设置动画效果
        syBanner.setBannerAnimation(Transformer.DepthPage);
        //设置轮播时间
        syBanner.setDelayTime(3000);
        //开始轮播
        syBanner.start();

        syBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", l.get(position).getUrl());
                startActivity(intent);
                Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //请求数据
        syPresenter = new SyPresenter(getContext(), this);
        syPresenter.getFlData("http://120.27.23.105/product/getCatagory");
        syPresenter.getBannerImg("http://120.27.23.105/ad/getAd");

        //京东快报
        kb();


    }

    //banner请求成功的数据和为你推荐，京东秒杀的数据
    @Override
    public void onSuccess(SyBean json) {

        List<SyBean.DataBean> data = json.getData();
        List<SyBean.TuijianBean.ListBean> list = json.getTuijian().getList();

        String name = json.getTuijian().getName();
        syTitle.setText(name);


        syRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        syRecyclerView.setAdapter(new SyTjAdapter(list, getContext()));

        //   设置banner
        setBanner(data);

    }

    @Override
    public void onFailed(String json) {
        //网络不好的情况下，请求失败

    }


    //分类请求成功
    @Override
    public void onflSuccess(SyflBean syflBean) {
        Log.d("WXW", syflBean.getData().size() + "aaa");

        //数据加载成功，将数据加载到适配器中
        //网格布局
        flRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));

        flRecyclerView.setAdapter(new FlAdapter(syflBean.getData(), getContext()));

    }

    //分类请求失败
    @Override
    public void onflFailed(String err) {

    }


    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.syQRcode:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;

            case R.id.sySearch:
                Intent intent1 = new Intent(getActivity(), SySearchActivity.class);
                startActivity(intent1);
                break;


        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //QRcode
        if (requestCode == REQUEST_CODE) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //快报
    public void kb() {
        List<String> notices = new ArrayList<>();
        notices.add("大促销下单拆福袋，亿万新年红包随便拿");
        notices.add("家电五折团，抢十亿无门槛现金红包");
        notices.add("星球大战剃须刀首发送200元代金券");
        noticeView.addNotice(notices);
        noticeView.startFlipping();
    }

    //防止内存泄露
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (syPresenter != null) {
            syPresenter.detatch();
        }
    }

}
