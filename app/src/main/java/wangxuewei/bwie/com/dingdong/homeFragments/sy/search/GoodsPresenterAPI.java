package wangxuewei.bwie.com.dingdong.homeFragments.sy.search;

import wangxuewei.bwie.com.dingdong.bean.GoodsListBean;

/**
 * Created by jim on 2017/12/16.
 */

public interface GoodsPresenterAPI {

    void success(GoodsListBean goodsListBean);

    void failed(String err);


}
