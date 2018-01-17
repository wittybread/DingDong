package wangxuewei.bwie.com.dingdong.homeFragments.fl.fladapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.FlRightBean;

/**
 * Created by jim on 2017/12/13.
 */

public class FlRightAdapter extends RecyclerView.Adapter<FlRightAdapter.MyViewHolder> {

    private List<FlRightBean.DataBean> list;
    private Context context;
    private FlRightDownAdapter flRightDownAdapter;

    public FlRightAdapter(List<FlRightBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public FlRightAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_flright, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(FlRightAdapter.MyViewHolder holder, int position) {

        holder.flRightItemText.setText(list.get(position).getName());

        flRightDownAdapter = new FlRightDownAdapter(context, list.get(position).getList());
        holder.flRightItemInRecycler.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        holder.flRightItemInRecycler.setAdapter(flRightDownAdapter);
        flRightDownAdapter.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView flRightItemText;
        private final RecyclerView flRightItemInRecycler;

        public MyViewHolder(View itemView) {
            super(itemView);

            flRightItemText = (TextView) itemView.findViewById(R.id.flRightItemText);
            flRightItemInRecycler = (RecyclerView) itemView.findViewById(R.id.flRightItemInRecycler);

        }
    }


}
