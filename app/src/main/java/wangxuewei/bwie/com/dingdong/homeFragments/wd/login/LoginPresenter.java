package wangxuewei.bwie.com.dingdong.homeFragments.wd.login;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.LoginBean;

/**
 * Created by jim on 2017/12/15.
 */

public class LoginPresenter {

    private LoginViewAPI loginViewAPI;
    private Context context;
    private final LoginModle loginModle;

    public LoginPresenter(LoginViewAPI loginViewAPI, Context context) {
        this.loginViewAPI = loginViewAPI;
        this.context = context;
        loginModle = new LoginModle();
    }

    public void getLoginData(String url, String name, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", name);
        map.put("password", password);

        loginModle.getLoginData(url, map, new LoginPresenterAPI() {
            @Override
            public void LoginSuccess(LoginBean bean) {
                loginViewAPI.onLoginSuccess(bean);
            }

            @Override
            public void LoginFailed(String err) {
                loginViewAPI.onLoginFailed(err);
            }
        });
    }


}
