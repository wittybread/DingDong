package wangxuewei.bwie.com.dingdong.homeFragments.sy;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.SyBean;
import wangxuewei.bwie.com.dingdong.bean.SyflBean;
import wangxuewei.bwie.com.dingdong.utils.CallBack;
import wangxuewei.bwie.com.dingdong.utils.OkHttpUtils;

/**
 * Created by jim on 2017/12/12.
 */

public class SyModle {

    //得到banner等数据
    public void getSyBean(String url, Map<String, String> map, final SyPresenterAPI syPresenterAPI) {

        OkHttpUtils.getInstance().doPost(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {
                Log.d("WXW", "fl1" + json);
                Gson gson = new Gson();
                SyBean syBean = gson.fromJson(json, SyBean.class);
                syPresenterAPI.success(syBean);

            }

            @Override
            public void onFailed(String err) {
                syPresenterAPI.failed(err);
            }
        });
    }

    //得到分类数据
    public void getSyFlBean(String url, Map<String, String> map, final SyflPresenterAPI syflPresenterAPI) {

        OkHttpUtils.getInstance().doPost(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {
                Log.d("WXW", "fl2" + json);
                Gson gson = new Gson();
                SyflBean syflBean = gson.fromJson(json, SyflBean.class);
                syflPresenterAPI.flSuccess(syflBean);
            }

            @Override
            public void onFailed(String err) {
                syflPresenterAPI.flFailed(err);
            }
        });

    }


}
