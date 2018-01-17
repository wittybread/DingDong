package wangxuewei.bwie.com.dingdong.homeFragments.sy;

import wangxuewei.bwie.com.dingdong.bean.SyBean;
import wangxuewei.bwie.com.dingdong.bean.SyflBean;

/**
 * Created by jim on 2017/12/12.
 */

public interface SyViewAPI {

    void onSuccess(SyBean json);

    void onFailed(String json);

    void onflSuccess(SyflBean syflBean);

    void onflFailed(String err);

}
