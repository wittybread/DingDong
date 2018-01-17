package wangxuewei.bwie.com.dingdong.homeFragments.wd.register;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import wangxuewei.bwie.com.dingdong.bean.RegisterBean;

/**
 * Created by jim on 2017/12/15.
 */

public class RegisterPresenter {

    private RegisterViewAPI registerViewAPI;
    private Context context;
    private final RegisterModle registerModle;

    public RegisterPresenter(RegisterViewAPI registerViewAPI, Context context) {
        this.registerViewAPI = registerViewAPI;
        this.context = context;
        registerModle = new RegisterModle();
    }

    public void getRegisterData(String url, String mobile, String password) {

        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("password", password);

        registerModle.getRegister(url, map, new RegisterPresenterAPI() {
            @Override
            public void RegisterSuccess(RegisterBean bean) {
                registerViewAPI.onRegisterSuccess(bean);
            }

            @Override
            public void RegisterFailed(String err) {
                registerViewAPI.onRegisterFailed(err);
            }
        });


    }


}
