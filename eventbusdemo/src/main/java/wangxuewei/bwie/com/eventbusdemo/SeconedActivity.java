package wangxuewei.bwie.com.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SeconedActivity extends AppCompatActivity {

    private TextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconed);
        viewById = (TextView) findViewById(R.id.text1);
        EventBus.getDefault().register(this);


    }

    @Subscribe(sticky = true)
    public void onEventMainThread(String event) {
        String msg = "接受到了消息" + event;
        Log.d("harvic", msg);
        viewById.setText(event);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
