package wangxuewei.bwie.com.dingdong.homeFragments.sy;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.SyBean;
import wangxuewei.bwie.com.dingdong.bean.SyflBean;

/**
 * Created by jim on 2017/12/12.
 */

public class SyPresenter {

    private Context context;

    private SyViewAPI syViewAPI;
    SyModle syModle = new SyModle();
    Map<String, String> map = new HashMap<>();

    //构造方法
    public SyPresenter(Context context, SyViewAPI syViewAPI) {
        this.context = context;
        this.syViewAPI = syViewAPI;
    }

    //得到banner等数据
    public void getBannerImg(String url) {

        syModle.getSyBean(url, map, new SyPresenterAPI() {
            @Override
            public void success(SyBean syBean) {
                syViewAPI.onSuccess(syBean);
            }

            @Override
            public void failed(String err) {
                syViewAPI.onFailed(err);
            }

        });
    }

    //得到banner下分类的数据
    public void getFlData(String url) {
        syModle.getSyFlBean(url, map, new SyflPresenterAPI() {
            @Override
            public void flSuccess(SyflBean syflBean) {
                syViewAPI.onflSuccess(syflBean);
            }

            @Override
            public void flFailed(String err) {
                syViewAPI.onflFailed(err);
            }
        });
    }







    //防止内存泄露
    public void detatch() {
        if (syViewAPI != null) {
            syViewAPI = null;
        }
    }


}
