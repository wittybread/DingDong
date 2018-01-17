package wangxuewei.bwie.com.dingdong.homeFragments.wd.register;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.RegisterBean;
import wangxuewei.bwie.com.dingdong.utils.CallBack;
import wangxuewei.bwie.com.dingdong.utils.OkHttpUtils;

/**
 * Created by jim on 2017/12/15.
 */

public class RegisterModle {

    public void getRegister(String url, Map<String, String> map, final RegisterPresenterAPI registerPresenterAPI) {

        OkHttpUtils.getInstance().doPost(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {
                Gson gson = new Gson();
                RegisterBean registerBean = gson.fromJson(json, RegisterBean.class);
                Log.d("RG", registerBean.getMsg());
                registerPresenterAPI.RegisterSuccess(registerBean);
            }

            @Override
            public void onFailed(String err) {
                registerPresenterAPI.RegisterFailed(err);
            }
        });

    }

}
