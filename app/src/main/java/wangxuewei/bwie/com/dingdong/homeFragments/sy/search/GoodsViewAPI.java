package wangxuewei.bwie.com.dingdong.homeFragments.sy.search;

import wangxuewei.bwie.com.dingdong.bean.GoodsListBean;

/**
 * Created by jim on 2017/12/16.
 */

public interface GoodsViewAPI {

    void onSuccess(GoodsListBean goodsListBean);

    void onFailed(String err);

}
