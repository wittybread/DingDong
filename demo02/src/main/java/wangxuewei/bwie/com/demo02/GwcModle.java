package wangxuewei.bwie.com.demo02;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import wangxuewei.bwie.com.demo02.bean.CartBean;

/**
 * Created by jim on 2017/12/19.
 */

public class GwcModle {

    public void getCarShopData(String url, Map<String, String> map, final GwcPresenterAPI gwcPresenterAPI) {
        OkHttpUtils.getInstance().doPost(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {
                Log.d("GWC", json);
                if (!json.equals("null")) {
                    Gson gson = new Gson();
                    CartBean shoppingCar = gson.fromJson(json, CartBean.class);
                    gwcPresenterAPI.getCarDataSuccess(shoppingCar);
                    gwcPresenterAPI.getNullCar(false);
                } else {
                    gwcPresenterAPI.getNullCar(true);
                }
            }

            @Override
            public void onFailed(String err) {
                gwcPresenterAPI.getCarDataFailed(err);
            }
        });
    }

}
