package wangxuewei.bwie.com.dingdong.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.CarBean;
import wangxuewei.bwie.com.dingdong.bean.GoodsBean;

public class DetailActivity extends AppCompatActivity implements DetailViewAPI {
    private SimpleDraweeView img;
    private TextView title;
    private TextView price;
    private TextView bargainPrice;
    private Button addCar;
    private SharedPreferences sp;
    private boolean loginSure;
    private int uid;
    private int pid;
    String u = "https://www.zhaoapi.cn/product/getProductDetail";
    String u2 = "https://www.zhaoapi.cn/product/addCart";
    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", 0);
        initView();

        detailPresenter = new DetailPresenter(this, this);
        detailPresenter.getDetailData(u, pid + "");
    }


    //点击事件
    private void clickLietener() {

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailPresenter.getCarData(u2, uid + "", pid + "");
                Toast.makeText(DetailActivity.this, "加购成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        img = (SimpleDraweeView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);
        price = (TextView) findViewById(R.id.price);
        bargainPrice = (TextView) findViewById(R.id.bargainPrice);
        addCar = (Button) findViewById(R.id.addCar);

    }


    public void setView(GoodsBean.DataBean l) {

        title.setText(l.getTitle());
        price.setText(l.getPrice() + "");
        bargainPrice.setText(l.getBargainPrice() + "");
        String images = l.getImages();
        String[] split = images.split("\\|");

        Uri uri = Uri.parse(split[0]);
        img.setImageURI(uri);

    }

    public void shareP() {
        sp = getSharedPreferences("loginMsg", Context.MODE_PRIVATE);
        loginSure = sp.getBoolean("loginSure", false);
        uid = sp.getInt("uid", -1);
        if (loginSure) {
            clickLietener();
        } else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAddSuccess(CarBean carBean) {
        Log.d("TJ", carBean.getMsg() + "car");

    }

    @Override
    public void onAddFailed(String e) {
        //网络原因，添加失败
    }

    @Override
    public void onDetailSuccess(GoodsBean goodsBean) {
        setView(goodsBean.getData());


    }

    @Override
    public void onDetailFailed(String e) {
        Log.d("TJ", e);

    }

    @Override
    protected void onResume() {
        super.onResume();
        shareP();
    }
}