package wangxuewei.bwie.com.demo03;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jim on 2017/12/27.
 */

public class MyAdapter extends BaseExpandableListAdapter {

    private List<String> g_list;
    private List<List<UserInfo>> c_list;
    private Context context;

    public MyAdapter(List<String> g_list, List<List<UserInfo>> c_list, Context context) {
        this.g_list = g_list;
        this.c_list = c_list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return g_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return c_list.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return g_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return c_list.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        gViewHolder gholder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.group_layout, null);

            gholder = new gViewHolder();

            gholder.g_text = (TextView) convertView
                    .findViewById(R.id.group_text);

            gholder.g_img = (ImageView) convertView
                    .findViewById(R.id.group_img);
            convertView.setTag(gholder);

        } else {
            gholder = (gViewHolder) convertView.getTag();
        }

        gholder.g_text.setText(g_list.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        cViewHolder cHolder;
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.child_layout, null);

            cHolder = new cViewHolder();

            cHolder.c_img = (ImageView) convertView
                    .findViewById(R.id.child_img);
            cHolder.c_name = (TextView) convertView
                    .findViewById(R.id.child_name);
            cHolder.c_job = (TextView) convertView.findViewById(R.id.child_job);

            convertView.setTag(cHolder);

        } else {
            cHolder = (cViewHolder) convertView.getTag();
        }

        cHolder.c_img.setImageResource(c_list.get(groupPosition)
                .get(childPosition).getImg());
        cHolder.c_name.setText(c_list.get(groupPosition).get(childPosition)
                .getName());
        cHolder.c_job.setText(c_list.get(groupPosition).get(childPosition)
                .getJob());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    // 分组的ViewHolder
    class gViewHolder {
        ImageView g_img;
        TextView g_text;
    }

    class cViewHolder {
        ImageView c_img;
        TextView c_name;
        TextView c_job;

    }
}
