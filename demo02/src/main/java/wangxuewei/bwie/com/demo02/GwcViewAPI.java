package wangxuewei.bwie.com.demo02;


import wangxuewei.bwie.com.demo02.bean.CartBean;

/**
 * Created by jim on 2017/12/19.
 */

public interface GwcViewAPI {

    void onGetCarDataSuccess(CartBean shoppingCar);

    void onGetNullCar(Boolean empty);

    void onGetCarDataFailed(String s);

}
