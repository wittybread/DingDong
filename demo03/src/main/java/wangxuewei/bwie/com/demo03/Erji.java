package wangxuewei.bwie.com.demo03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class Erji extends AppCompatActivity {

    private ExpandableListView exLv;
    private ArrayList<List<UserInfo>> c_lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erji);

        exLv = (ExpandableListView) findViewById(R.id.exLv);

        // 要有分组的数据
        List<String> g_list = new ArrayList<String>();
        g_list.add("魏国");
        g_list.add("蜀国");
        g_list.add("吴国");
        c_lists = new ArrayList<List<UserInfo>>();

        List<UserInfo> c_list_01 = new ArrayList<UserInfo>();
        c_list_01.add(new UserInfo(R.mipmap.ic_launcher, "郭嘉", "鬼才"));
        c_list_01.add(new UserInfo(R.mipmap.ic_launcher, "曹操", "枭雄"));
        c_list_01.add(new UserInfo(R.mipmap.ic_launcher, "夏侯惇", "将军"));
        c_list_01.add(new UserInfo(R.mipmap.ic_launcher, "贾诩", "毒才"));

        List<UserInfo> c_list_02 = new ArrayList<UserInfo>();
        c_list_02.add(new UserInfo(R.mipmap.ic_launcher, "刘备", "孬种"));
        c_list_02.add(new UserInfo(R.mipmap.ic_launcher, "赵云", "常胜将军"));
        c_list_02.add(new UserInfo(R.mipmap.ic_launcher, "诸葛亮", "卧龙"));
        c_list_02.add(new UserInfo(R.mipmap.ic_launcher, "关羽", "武圣"));

        List<UserInfo> c_list_03 = new ArrayList<UserInfo>();
        c_list_03.add(new UserInfo(R.mipmap.ic_launcher, "孙权", "扛把子"));
        c_list_03.add(new UserInfo(R.mipmap.ic_launcher, "孙尚香", "美女"));
        c_list_03.add(new UserInfo(R.mipmap.ic_launcher, "黄盖", "不死小强"));
        c_list_03.add(new UserInfo(R.mipmap.ic_launcher, "周瑜", "提督"));

        // 要把所有的子条目数据放到集合中

        c_lists.add(c_list_01);
        c_lists.add(c_list_02);
        c_lists.add(c_list_03);

        MyAdapter myAdapter = new MyAdapter(g_list, c_lists, this);

        exLv.setAdapter(myAdapter);
        exLv.setGroupIndicator(null);
        //一级列表固定展开
        for (int i = 0; i < g_list.size(); i++) {
            exLv.expandGroup(i);
        }
    }
}
