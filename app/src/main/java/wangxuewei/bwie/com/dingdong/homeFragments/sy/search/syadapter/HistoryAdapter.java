package wangxuewei.bwie.com.dingdong.homeFragments.sy.search.syadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jim on 2017/12/18.
 */

public class HistoryAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public HistoryAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // 只需要加载一次，，不需要重复加载
        if (convertView == null) {
            convertView = View.inflate(context, android.R.layout.simple_list_item_1, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 赋值
        holder.name.setText(list.get(position));
        // 返回视图
        return convertView;

    }

    // 进行优化，，存放控件
    private class ViewHolder {
        TextView name;
    }
}
