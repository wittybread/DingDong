package wangxuewei.bwie.com.dingdong.homeFragments.fl;

import wangxuewei.bwie.com.dingdong.bean.FlRightBean;

/**
 * Created by jim on 2017/12/13.
 */

public interface FlPresenterAPI {

    void onFlSuccess(FlRightBean flRightBean);

    void onFlFailed(String err);

}
