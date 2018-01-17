package wangxuewei.bwie.com.dingdong.homeFragments.fx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import wangxuewei.bwie.com.dingdong.R;

/**
 * Created by jim on 2017/12/8.
 */


public class FxFragment extends Fragment {

    private WebView fxWebView;
    private String url = "https://h5.m.jd.com/active/faxian/list/article-list.html";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fx, null);

        fxWebView = (WebView) view.findViewById(R.id.fxWebView);
        WebSettings webSettings = fxWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


        fxWebView.loadUrl(url);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
