package wangxuewei.bwie.com.dingdong.homeFragments.sy.search;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.GoodsListBean;

/**
 * Created by jim on 2017/12/16.
 */

public class GoodsPresenter {

    private Context context;
    private GoodsViewAPI goodsViewAPI;
    private final GoodsModle goodsModle;

    public GoodsPresenter(Context context, GoodsViewAPI goodsViewAPI) {
        this.context = context;
        this.goodsViewAPI = goodsViewAPI;
        goodsModle = new GoodsModle();
    }

    public void getGoodsListData(String url, String keywords, String page) {
        Map<String, String> map = new HashMap<>();
        map.put("source", "android");
        map.put("keywords", keywords);
        map.put("page", page);
        goodsModle.getGoodsListData(url, map, new GoodsPresenterAPI() {
            @Override
            public void success(GoodsListBean goodsListBean) {
                goodsViewAPI.onSuccess(goodsListBean);
            }

            @Override
            public void failed(String err) {
                goodsViewAPI.onFailed(err);
            }
        });
    }


}
