package wangxuewei.bwie.com.dingdong.homeFragments.wd.register;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.RegisterBean;
import wangxuewei.bwie.com.dingdong.homeFragments.wd.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterViewAPI, View.OnClickListener {

    private ImageView registerBack;
    private Button registerBtn;
    private EditText registerEditMobile;
    private EditText registerEditPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //加载组件
        initView();
        //加载监听
        clickListener();


    }

    private void clickListener() {

        registerBack.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        registerBack = (ImageView) findViewById(R.id.registerBack);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerEditMobile = (EditText) findViewById(R.id.registerEditMobile);
        registerEditPassword = (EditText) findViewById(R.id.registerEditPassword);

    }

    @Override
    public void onRegisterSuccess(RegisterBean registerBean) {
        int code = Integer.parseInt(registerBean.getCode());
        String msg = registerBean.getMsg();
        if (code == 0) {
            //注册成功
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRegisterFailed(String err) {
        Toast.makeText(this, "网络出问题了！ = =", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerBack:
                //注册界面，返回登录界面
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;
            case R.id.registerBtn:
                //注册按钮
                String mobile = registerEditMobile.getText().toString();
                String password = registerEditPassword.getText().toString();
                RegisterPresenter rp = new RegisterPresenter(this, this);
                rp.getRegisterData("http://120.27.23.105/user/reg", mobile, password);

                break;
        }
    }
}
