package wangxuewei.bwie.com.dingdong;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import wangxuewei.bwie.com.dingdong.View.HomeActivity;

/**
 * 引导页
 * 1.设置计时器，3秒后跳转到主页面
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 定义一个handler
     */

    private final int DELAY_TIME = 3000;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /**
             *三秒后接收到到一个空消息，进行跳转
             */
//            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            Intent intent = new Intent(MainActivity.this, MyPasswordActivity.class);
            startActivity(intent);
            finish();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        /**
         *延时发送一条空消息，设置为三秒
         */
        handler.sendEmptyMessageDelayed(0, DELAY_TIME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 界面销毁，清除handler
         */
        handler.removeCallbacksAndMessages(handler);
    }


}
