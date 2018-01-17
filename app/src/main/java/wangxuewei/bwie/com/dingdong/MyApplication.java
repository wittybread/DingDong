package wangxuewei.bwie.com.dingdong;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import wangxuewei.bwie.com.dingdong.utils.ImageLoaderUtils;

/**
 * Created by jim on 2017/12/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化ImageLoaderUtils
        ImageLoaderUtils.initConfig(this);


        Fresco.initialize(this);

    }
}
