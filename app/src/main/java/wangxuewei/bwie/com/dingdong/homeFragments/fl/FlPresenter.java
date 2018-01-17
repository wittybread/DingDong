package wangxuewei.bwie.com.dingdong.homeFragments.fl;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.FlRightBean;

/**
 * Created by jim on 2017/12/13.
 */

public class FlPresenter {

    private FlViewAPI flViewAPI;
    private Context context;
    private final FlModle flModle;

    private Map<String, String> map = new HashMap<>();

    //构造方法
    public FlPresenter(FlViewAPI flViewAPI, Context context) {
        this.flViewAPI = flViewAPI;
        this.context = context;
        flModle = new FlModle();
    }

    //得到数据，回调到View
    public void getFLRightData(String url, int cid) {
        map.put("cid", cid + "");
        flModle.getRightItemData(url, map, new FlPresenterAPI() {
            @Override
            public void onFlSuccess(FlRightBean flRightBean) {
                flViewAPI.onFlSuccess(flRightBean);
            }

            @Override
            public void onFlFailed(String err) {
                flViewAPI.onFlFailed(err);
            }
        });
    }

    public void detach() {
        if (flViewAPI != null) {
            flViewAPI = null;
        }
    }


}
