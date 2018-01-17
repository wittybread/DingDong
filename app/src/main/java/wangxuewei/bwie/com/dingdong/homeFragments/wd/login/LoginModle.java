package wangxuewei.bwie.com.dingdong.homeFragments.wd.login;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.LoginBean;
import wangxuewei.bwie.com.dingdong.utils.CallBack;
import wangxuewei.bwie.com.dingdong.utils.OkHttpUtils;

/**
 * Created by jim on 2017/12/15.
 */

public class LoginModle {

    public void getLoginData(String url, Map<String, String> map, final LoginPresenterAPI loginPresenterAPI) {

        OkHttpUtils.getInstance().doPost(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                Log.d("LG", "M" + loginBean.getMsg());
                loginPresenterAPI.LoginSuccess(loginBean);
            }

            @Override
            public void onFailed(String err) {
                loginPresenterAPI.LoginFailed(err);
            }
        });


    }


}
