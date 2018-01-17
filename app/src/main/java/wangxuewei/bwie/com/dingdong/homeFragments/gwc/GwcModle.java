package wangxuewei.bwie.com.dingdong.homeFragments.gwc;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.CartBean;
import wangxuewei.bwie.com.dingdong.utils.CallBack;
import wangxuewei.bwie.com.dingdong.utils.OkHttpUtils;

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

    //删除购物车
    public void delGwcData(String url, Map<String, String> map, final GwcPresenterAPI gwcPresenterAPI) {
        OkHttpUtils.getInstance().okGet(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {
                Log.d("WXW", json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    Log.d("WXW", code);
                    if (code.equals("0")) {
                        gwcPresenterAPI.onDelSuccess(true);
                    } else if (code.equals("1")) {
                        gwcPresenterAPI.onDelSuccess(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(String err) {
                gwcPresenterAPI.onDelFailed(err);
            }
        });
    }

}
