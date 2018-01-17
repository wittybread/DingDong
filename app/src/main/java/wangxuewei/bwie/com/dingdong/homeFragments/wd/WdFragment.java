package wangxuewei.bwie.com.dingdong.homeFragments.wd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.homeFragments.wd.login.LoginActivity;

/**
 * Created by jim on 2017/12/8.
 */

public class WdFragment extends Fragment implements View.OnClickListener {

    private TextView loginOrRegiser;
    private SharedPreferences sp;
    private boolean loginSure;
    private String username;
    private int uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wd, null);

        initView(view);

        return view;
    }

    public void shareP() {
        sp = getContext().getSharedPreferences("loginMsg", Context.MODE_PRIVATE);
        loginSure = sp.getBoolean("loginSure", false);
        username = sp.getString("username", "登录/注册");
        uid = sp.getInt("uid", -1);

        if (loginSure) {
            loginOrRegiser.setText(username);
        } else {
            loginOrRegiser.setText("登录/注册");
        }
    }

    private void initView(View view) {
        loginOrRegiser = (TextView) view.findViewById(R.id.loginOrRegiser);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginOrRegiser.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginOrRegiser:
                if (loginSure) {
                    Intent intent2 = new Intent(getActivity(), UserSettingActivity.class);
                    intent2.putExtra("name", username);
                    startActivity(intent2);
                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        shareP();
    }
}
