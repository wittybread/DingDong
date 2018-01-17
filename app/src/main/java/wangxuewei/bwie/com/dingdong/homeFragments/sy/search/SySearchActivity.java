package wangxuewei.bwie.com.dingdong.homeFragments.sy.search;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.homeFragments.sy.search.syadapter.HistoryAdapter;
import wangxuewei.bwie.com.dingdong.sqlite.MySqliteHelper;

public class SySearchActivity extends AppCompatActivity {

    private MyTitleView myTitle;
    private EditText name;
    private MyFlowLayout hotSearch;
    private ListView lv;
    private Button btnClearHistoryLv;
    private List<String> list = new ArrayList<String>();
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy_search);
        //加载控件
        initView();
        //点击事件
        clickListener();

    }

    public void clickListener() {
        //标题栏点击事件
        myTitle.setOnTitleViewClickListenler(new MyTitleView.OnTitleViewClickListener() {
            @Override
            public void titleViewButtonClicked(View v) {
                //搜索界面跳转到列表页面
                String na = name.getText().toString();

                if (na.trim().equals("")) {
                    Toast.makeText(SySearchActivity.this, "请输入信息", Toast.LENGTH_SHORT).show();
                } else {
                    //将数据插入数据库
                    insertSqlite(na);

                    Intent intent = new Intent(SySearchActivity.this, ShowGoodsActivity.class);
                    intent.putExtra("key", na);
                    startActivity(intent);
                }


            }

            @Override
            public void titleViewBackClicked(View v) {
                finish();
            }

            @Override
            public void titleViewEditClicked(View v) {
                //Toast.makeText(SySearchActivity.this, "edit", Toast.LENGTH_SHORT).show();
                /**
                 *输入框点击事件
                 */

            }
        });

        btnClearHistoryLv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySqliteHelper mySqliteHelper = new MySqliteHelper(SySearchActivity.this);
                SQLiteDatabase wd = mySqliteHelper.getWritableDatabase();
                wd.delete("history", null, null);
                wd.close();
                list.clear();
                historyAdapter.notifyDataSetChanged();
            }
        });


    }

    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        myTitle = (MyTitleView) findViewById(R.id.MyTitle);
        name = (EditText) myTitle.findViewById(R.id.sySearchEdit);
        hotSearch = (MyFlowLayout) findViewById(R.id.hotSearch);
        lv = (ListView) findViewById(R.id.historyLv);
        btnClearHistoryLv = (Button) findViewById(R.id.btnClearHistoryLv);
    }

    public void insertSqlite(String na) {
        //将输入的信息存入数据库
        MySqliteHelper mySqliteHelper = new MySqliteHelper(SySearchActivity.this);
        SQLiteDatabase wd = mySqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("edit", na);
        wd.insert("history", null, values);
        wd.close();
    }

    public void seleSqlite() {
        list.clear();
        //将查询的数据放入集合
        MySqliteHelper mySqliteHelper = new MySqliteHelper(SySearchActivity.this);
        SQLiteDatabase rd = mySqliteHelper.getReadableDatabase();
        // 查询数据
        Cursor cursor = rd.query("history", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("edit"));
            // 将数据放入集合
            list.add(name);
        }
        rd.close();
        historyAdapter = new HistoryAdapter(list, this);
        lv.setAdapter(historyAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
//        查询数据库
        seleSqlite();

    }
}
