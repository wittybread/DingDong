package wangxuewei.bwie.com.dingdong.View;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.CarBean;
import wangxuewei.bwie.com.dingdong.bean.GoodsBean;

/**
 * Created by jim on 2017/12/19.
 */

public class DetailPresenter {

    private DetailViewAPI detailViewAPI;
    private Context context;
    private final DetailModel detailModel;

    public DetailPresenter(DetailViewAPI detailViewAPI, Context context) {
        this.detailViewAPI = detailViewAPI;
        this.context = context;
        detailModel = new DetailModel();
    }

    public void getCarData(String url, String uid, String pid) {
        Map<String, String> map = new HashMap<>();
        map.put("source", "android");
        map.put("uid", uid);
        map.put("pid", pid);
        detailModel.getAddCarData(url, map, new DetailPresenterAPI() {
            @Override
            public void addSuccess(CarBean carBean) {
                detailViewAPI.onAddSuccess(carBean);
            }

            @Override
            public void addFailed(String e) {
                detailViewAPI.onAddFailed(e);
            }

        });

    }

    public void getDetailData(String url, String pid) {
        Map<String, String> map = new HashMap<>();
        map.put("source", "android");
        map.put("pid", pid);
        detailModel.getDetailData(url, map, new DetailPresenterAPI2() {
            @Override
            public void detailSuccess(GoodsBean goodsBean) {
                detailViewAPI.onDetailSuccess(goodsBean);
            }

            @Override
            public void detailFailed(String e) {
                detailViewAPI.onDetailFailed(e);
            }
        });
    }


}
