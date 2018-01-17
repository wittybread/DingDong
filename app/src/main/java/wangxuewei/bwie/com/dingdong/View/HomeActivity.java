package wangxuewei.bwie.com.dingdong.View;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.homeFragments.fl.FlFragment;
import wangxuewei.bwie.com.dingdong.homeFragments.fx.FxFragment;
import wangxuewei.bwie.com.dingdong.homeFragments.gwc.GwcFragment;
import wangxuewei.bwie.com.dingdong.homeFragments.sy.SyFragment;
import wangxuewei.bwie.com.dingdong.homeFragments.wd.WdFragment;

public class HomeActivity extends AppCompatActivity {

    private BottomTabBar homeBottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        //查找控件//配置
        inits();

    }

    //查找组件
    private void inits() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        homeBottomTabBar = (BottomTabBar) findViewById(R.id.homeBottomTabBar);
        //配置底部按钮
        homeBottomTabBar.init(getSupportFragmentManager())
                .setImgSize(70, 70)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("首页", R.drawable.x_sy_b, SyFragment.class)
                .addTabItem("分类", R.drawable.x_fl_b, FlFragment.class)
                .addTabItem("发现", R.drawable.x_fx_b, FxFragment.class)
                .addTabItem("购物车", R.drawable.x_gw_b, GwcFragment.class)
                .addTabItem("我的", R.drawable.x_wd_b, WdFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });


    }
}
