package wangxuewei.bwie.com.dingdong.homeFragments.wd.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.LoginBean;
import wangxuewei.bwie.com.dingdong.homeFragments.wd.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginViewAPI, View.OnClickListener {

    private ImageView loginClose;
    private EditText loginEditMobile;
    private EditText loginEditPassword;
    private TextView loginToRegiser;
    private Button loginBtn;
    private LoginBean.DataBean data;
    private ImageView login_qq;

    private static final String TAG = "LoginActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //加载控件
        initView();
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this.getApplicationContext());

        //点击事件
        clickListener();


    }

    private void clickListener() {
        loginBtn.setOnClickListener(this);
        loginClose.setOnClickListener(this);
        loginToRegiser.setOnClickListener(this);
        login_qq.setOnClickListener(this);
    }


    //加载布局
    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        loginClose = (ImageView) findViewById(R.id.loginClose);
        loginEditMobile = (EditText) findViewById(R.id.loginEditMobile);
        loginEditPassword = (EditText) findViewById(R.id.loginEditPassword);
        loginToRegiser = (TextView) findViewById(R.id.loginToRegiser);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        login_qq = (ImageView) findViewById(R.id.login_QQ);
    }


    @Override
    public void onLoginSuccess(LoginBean bean) {
        data = bean.getData();
        int code = Integer.parseInt(bean.getCode());
        String msg = bean.getMsg();
        if (code == 0) {
            //登录成功
            Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
            shareP(data);
            finish();
        } else {
            Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();


        }


    }

    public void shareP(LoginBean.DataBean bean) {
        if (bean != null) {
            SharedPreferences sp2 = this.getSharedPreferences("loginMsg", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sp2.edit();
            editor2.putBoolean("loginSure", true);
            editor2.putString("username", bean.getUsername());
            editor2.putInt("uid", bean.getUid());
            editor2.commit();
        }

    }

    @Override
    public void onLoginFailed(String err) {

    }

    //按钮点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                String mobile = loginEditMobile.getText().toString();
                String password = loginEditPassword.getText().toString();
                LoginPresenter lp = new LoginPresenter(this, this);
                lp.getLoginData("http://120.27.23.105/user/login", mobile, password);

                break;
            case R.id.loginClose:
                //返回图标，退出登录界面

                finish();
                break;
            case R.id.loginToRegiser:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_QQ:
                //第三方登录
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this, "all", mIUiListener);

                break;
        }
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        private UserInfo mUserInfo;

        @Override
        public void onComplete(Object o) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            JSONObject obj = (JSONObject) o;

            try {

                String openid = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");

                mTencent.setOpenId(openid);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {

                    private String figureurl_qq_2;
                    private String gender;
                    private String nickname;

                    @Override
                    public void onComplete(Object o) {

                        Log.i(TAG, "登录成功");
                        Log.i(TAG, "登录成功" + o.toString());

                        if (o == null) {
                            return;
                        }
                        JSONObject jsonObject = (JSONObject) o;
                        try {
                            nickname = jsonObject.getString("nickname");
                            gender = jsonObject.getString("gender");
                            figureurl_qq_2 = jsonObject.getString("figureurl_qq_2");
                            Log.i(TAG, "登录成功" + nickname + gender);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//                        Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
//                        intent.putExtra("nickname", nickname);
//                        intent.putExtra("gender", gender);
//                        intent.putExtra("figureurl_qq_2", figureurl_qq_2);
//                        startActivity(intent);

                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.i(TAG, "登录失败");
                    }

                    @Override
                    public void onCancel() {
                        Log.i(TAG, "登录取消");
                    }
                });


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Log.i(TAG, "授权失败");
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "授权取消");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        Log.i(TAG, "onActivityResult");

    }

}
