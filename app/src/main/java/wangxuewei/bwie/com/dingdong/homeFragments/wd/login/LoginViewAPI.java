package wangxuewei.bwie.com.dingdong.homeFragments.wd.login;

import wangxuewei.bwie.com.dingdong.bean.LoginBean;

/**
 * Created by jim on 2017/12/15.
 */

public interface LoginViewAPI {

    void onLoginSuccess(LoginBean json);

    void onLoginFailed(String err);


}
