package wangxuewei.bwie.com.dingdong.homeFragments.gwc;

import wangxuewei.bwie.com.dingdong.bean.CartBean;

/**
 * Created by jim on 2017/12/19.
 */

public interface GwcViewAPI {

    void onGetCarDataSuccess(CartBean shoppingCar);

    void onGetNullCar(Boolean empty);

    void onGetCarDataFailed(String s);

}
