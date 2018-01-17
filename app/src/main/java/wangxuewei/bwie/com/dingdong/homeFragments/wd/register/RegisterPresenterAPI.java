package wangxuewei.bwie.com.dingdong.homeFragments.wd.register;

import wangxuewei.bwie.com.dingdong.bean.RegisterBean;

/**
 * Created by jim on 2017/12/15.
 */

public interface RegisterPresenterAPI {

    void RegisterSuccess(RegisterBean bean);

    void RegisterFailed(String err);


}
