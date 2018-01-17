package wangxuewei.bwie.com.dingdong.View;

import com.google.gson.Gson;

import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.CarBean;
import wangxuewei.bwie.com.dingdong.bean.GoodsBean;
import wangxuewei.bwie.com.dingdong.utils.CallBack;
import wangxuewei.bwie.com.dingdong.utils.OkHttpUtils;

/**
 * Created by jim on 2017/12/19.
 */

public class DetailModel {

    public void getAddCarData(String url, Map<String, String> map, final DetailPresenterAPI detailPresenterAPI) {
        OkHttpUtils.getInstance().okGet(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {

                Gson gson = new Gson();
                CarBean carBean = gson.fromJson(json, CarBean.class);


                detailPresenterAPI.addSuccess(carBean);
            }

            @Override
            public void onFailed(String err) {
                detailPresenterAPI.addFailed(err);
            }
        });
    }

    public void getDetailData(String url, Map<String, String> map, final DetailPresenterAPI2 detailPresenterAPI) {
        OkHttpUtils.getInstance().okGet(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {
                Gson gson = new Gson();
                GoodsBean goodsBean = gson.fromJson(json, GoodsBean.class);

                detailPresenterAPI.detailSuccess(goodsBean);
            }

            @Override
            public void onFailed(String err) {
                detailPresenterAPI.detailFailed(err);
            }
        });
    }


}
