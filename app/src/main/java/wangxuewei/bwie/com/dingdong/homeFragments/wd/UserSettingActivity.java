package wangxuewei.bwie.com.dingdong.homeFragments.wd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.homeFragments.wd.address.AddressManage;

public class UserSettingActivity extends AppCompatActivity {

    private TextView name;
    private Button loginExit;
    private RelativeLayout addressManage;
    private ImageView userBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        Intent intent = getIntent();
        String names = intent.getStringExtra("name");

        name = (TextView) findViewById(R.id.settingUserName);
        loginExit = (Button) findViewById(R.id.loginExit);
        addressManage = (RelativeLayout) findViewById(R.id.addressManage);
        userBack = (ImageView) findViewById(R.id.user_back);


        name.setText(names);
        loginExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UserSettingActivity.this)
                        .setTitle("提示")
                        .setMessage("确定退出登录吗?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences sp = getSharedPreferences("loginMsg", Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sp.edit();
                                editor.clear();
                                editor.commit();
                                finish();
                            }
                        }).setNegativeButton("否", null)
                        .create().show();
            }
        });

        addressManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserSettingActivity.this, AddressManage.class);
                startActivity(intent1);
            }
        });

        userBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
