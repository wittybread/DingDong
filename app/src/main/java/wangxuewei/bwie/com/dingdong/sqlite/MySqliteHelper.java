package wangxuewei.bwie.com.dingdong.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jim on 2017/12/18.
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(Context context) {
        super(context, "historySearch.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table history (id integer primary key autoincrement ,edit text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
