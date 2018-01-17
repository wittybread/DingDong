package wangxuewei.bwie.com.dingdong.View;

import wangxuewei.bwie.com.dingdong.bean.CarBean;
import wangxuewei.bwie.com.dingdong.bean.GoodsBean;

/**
 * Created by jim on 2017/12/19.
 */

public interface DetailViewAPI {

    void onAddSuccess(CarBean carBean);

    void onAddFailed(String e);

    void onDetailSuccess(GoodsBean goodsBean);

    void onDetailFailed(String e);

}
