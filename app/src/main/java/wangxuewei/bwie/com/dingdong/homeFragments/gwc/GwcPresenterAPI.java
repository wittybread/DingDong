package wangxuewei.bwie.com.dingdong.homeFragments.gwc;

import wangxuewei.bwie.com.dingdong.bean.CartBean;

/**
 * Created by jim on 2017/12/19.
 */

public interface GwcPresenterAPI {
    void getCarDataSuccess(CartBean shoppingCar);

    void getNullCar(Boolean empty);

    void getCarDataFailed(String s);

    void onDelSuccess(Boolean flag);

    void onDelFailed(String err);
}
