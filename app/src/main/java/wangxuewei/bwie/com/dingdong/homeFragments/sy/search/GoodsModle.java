package wangxuewei.bwie.com.dingdong.homeFragments.sy.search;

import com.google.gson.Gson;

import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.GoodsListBean;
import wangxuewei.bwie.com.dingdong.utils.CallBack;
import wangxuewei.bwie.com.dingdong.utils.OkHttpUtils;

/**
 * Created by jim on 2017/12/16.
 */

public class GoodsModle {

    public void getGoodsListData(String url, Map<String, String> map, final GoodsPresenterAPI goodsPresenterAPI) {
        OkHttpUtils.getInstance().doPost(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {
                Gson gson = new Gson();
                GoodsListBean goodsListBean = gson.fromJson(json, GoodsListBean.class);
                goodsPresenterAPI.success(goodsListBean);
            }

            @Override
            public void onFailed(String err) {
                goodsPresenterAPI.failed(err);
            }
        });
    }


}
