package wangxuewei.bwie.com.dingdong.homeFragments.sy.syadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.SyflBean;

/**
 * Created by jim on 2017/12/13.
 */

public class FlAdapter extends RecyclerView.Adapter<FlAdapter.MyViewHolder> {

    private List<SyflBean.DataBean> list;
    private Context context;

    public FlAdapter(List<SyflBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public FlAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.syflitem, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(FlAdapter.MyViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getIcon()).into(holder.icon);
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView icon;
        private final TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.syFlItemImg);
            name = (TextView) itemView.findViewById(R.id.syFlItemName);
        }
    }

}
