package wangxuewei.bwie.com.dingdong.homeFragments.fl;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.FlRightBean;
import wangxuewei.bwie.com.dingdong.utils.CallBack;
import wangxuewei.bwie.com.dingdong.utils.OkHttpUtils;

/**
 * Created by jim on 2017/12/13.
 */

public class FlModle {

    //请求分类中右边的数据
    public void getRightItemData(String url, Map<String, String> map, final FlPresenterAPI flPresenterAPI) {
        OkHttpUtils.getInstance().doPost(url, map, new CallBack() {
            @Override
            public void onSuccess(String json) {
                Log.d("FL", json);
                Gson gson = new Gson();
                FlRightBean flRightBean = gson.fromJson(json, FlRightBean.class);
                flPresenterAPI.onFlSuccess(flRightBean);
            }

            @Override
            public void onFailed(String err) {
                flPresenterAPI.onFlFailed(err);
            }
        });
    }

}
