package wangxuewei.bwie.com.dingdong.homeFragments.wd.login;

import wangxuewei.bwie.com.dingdong.bean.LoginBean;

/**
 * Created by jim on 2017/12/15.
 */

public interface LoginPresenterAPI {

    void LoginSuccess(LoginBean json);

    void LoginFailed(String err);

}
