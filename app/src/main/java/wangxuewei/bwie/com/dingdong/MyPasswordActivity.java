package wangxuewei.bwie.com.dingdong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import wangxuewei.bwie.com.dingdong.View.HomeActivity;

public class MyPasswordActivity extends AppCompatActivity {
    private PatternLockView mPatternLockView;
    private SharedPreferences unlocking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_password);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        unlocking = getSharedPreferences("unlocking", Context.MODE_PRIVATE);

        if (!unlocking.getBoolean("firstUnlocking", false)) {
            SharedPreferences.Editor edit = unlocking.edit();
            edit.putBoolean("firstUnlocking", false);
            edit.commit();
        }
        mPatternLockView = (PatternLockView) findViewById(R.id.patter_lock_view3);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);


    }

    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
            Log.d("WXW", "start");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(mPatternLockView, progressPattern));
            Log.d("WXW", "onProgress" + progressPattern.size());
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));
            Log.d("WXW", "onComplete" + pattern.toString());
            boolean firstUnlocking = unlocking.getBoolean("firstUnlocking", false);
            if (!firstUnlocking) {
                if (pattern.size() < 4) {
                    Toast.makeText(MyPasswordActivity.this, "大于四个点", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor edit = unlocking.edit();
                    edit.putString("unlockingPwd", pattern.toString());
                    edit.putBoolean("firstUnlocking", true);
                    edit.commit();
                    Toast.makeText(MyPasswordActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                }

            } else {
                String unlockingPwd = unlocking.getString("unlockingPwd", "0");
                if (unlockingPwd.equals(pattern.toString())) {
                    Toast.makeText(MyPasswordActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MyPasswordActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MyPasswordActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
            Log.d("WXW", "onCleared");

        }
    };
}
