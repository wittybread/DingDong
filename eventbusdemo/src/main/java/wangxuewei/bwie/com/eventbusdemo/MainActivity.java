package wangxuewei.bwie.com.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    private Button btn_even;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_even = (Button) findViewById(R.id.btn_send);

        btn_even.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky("This is a EVENTBUS　MSG");

                Intent intent = new Intent(MainActivity.this, SeconedActivity.class);
                startActivity(intent);

            }
        });
    }
}
