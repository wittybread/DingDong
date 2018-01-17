package wangxuewei.bwie.com.dingdong.homeFragments.sy;

import wangxuewei.bwie.com.dingdong.bean.SyBean;

/**
 * Created by jim on 2017/12/12.
 */

public interface SyPresenterAPI {

    void success(SyBean json);

    void failed(String err);

}
