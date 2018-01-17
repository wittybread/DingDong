package wangxuewei.bwie.com.dingdong.homeFragments.gwc;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.CartBean;

/**
 * Created by jim on 2017/12/19.
 */

public class GwcPresenter {

    private GwcViewAPI gwcViewAPI;
    private Context context;
    private final GwcModle gwcModle;

    public GwcPresenter(GwcViewAPI gwcViewAPI, Context context) {
        this.gwcViewAPI = gwcViewAPI;
        this.context = context;
        gwcModle = new GwcModle();

    }

    public void getCarData(String url, String uid) {
        Map<String, String> map = new HashMap<>();
        map.put("source", "android");
        map.put("uid", uid);
        gwcModle.getCarShopData(url, map, new GwcPresenterAPI() {
            @Override
            public void getCarDataSuccess(CartBean shoppingCar) {
                gwcViewAPI.onGetCarDataSuccess(shoppingCar);
            }

            @Override
            public void getNullCar(Boolean empty) {
                gwcViewAPI.onGetNullCar(empty);
            }

            @Override
            public void getCarDataFailed(String s) {
                gwcViewAPI.onGetCarDataFailed(s);
            }

            @Override
            public void onDelSuccess(Boolean flag) {

            }

            @Override
            public void onDelFailed(String err) {

            }
        });
    }

    public void DelCar(String url, Map<String, String> map) {
        gwcModle.delGwcData(url, map, new GwcPresenterAPI() {

            @Override
            public void getCarDataSuccess(CartBean shoppingCar) {

            }

            @Override
            public void getNullCar(Boolean empty) {

            }

            @Override
            public void getCarDataFailed(String s) {

            }

            @Override
            public void onDelSuccess(Boolean flag) {

            }

            @Override
            public void onDelFailed(String err) {

            }
        });
    }

    public void destroy() {
        if (gwcViewAPI != null) {
            gwcViewAPI = null;
        }
    }

}
