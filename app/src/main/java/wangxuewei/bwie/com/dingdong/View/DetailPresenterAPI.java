package wangxuewei.bwie.com.dingdong.View;

import wangxuewei.bwie.com.dingdong.bean.CarBean;

/**
 * Created by jim on 2017/12/19.
 */

public interface DetailPresenterAPI {

    void addSuccess(CarBean carBean);

    void addFailed(String e);

}
