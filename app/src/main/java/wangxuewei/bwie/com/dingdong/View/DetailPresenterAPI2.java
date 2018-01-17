package wangxuewei.bwie.com.dingdong.View;

import wangxuewei.bwie.com.dingdong.bean.GoodsBean;

/**
 * Created by jim on 2017/12/19.
 */

public interface DetailPresenterAPI2 {

    void detailSuccess(GoodsBean goodsBean);

    void detailFailed(String e);


}
