package wangxuewei.bwie.com.demo02;


import wangxuewei.bwie.com.demo02.bean.CartBean;

/**
 * Created by jim on 2017/12/19.
 */

public interface GwcPresenterAPI {
    void getCarDataSuccess(CartBean shoppingCar);

    void getNullCar(Boolean empty);

    void getCarDataFailed(String s);
}
