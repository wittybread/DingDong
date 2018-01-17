package wangxuewei.bwie.com.dingdong.homeFragments.sy.syadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.View.DetailActivity;
import wangxuewei.bwie.com.dingdong.bean.SyBean;

/**
 * Created by jim on 2017/12/14.
 */

public class SyTjAdapter extends RecyclerView.Adapter<SyTjAdapter.MySyViewHolder> {

    private List<SyBean.TuijianBean.ListBean> list;
    private Context context;

    public SyTjAdapter(List<SyBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public SyTjAdapter.MySyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_sytuijian, parent, false);
        MySyViewHolder mySyViewHolder = new MySyViewHolder(view);

        return mySyViewHolder;
    }

    @Override
    public void onBindViewHolder(SyTjAdapter.MySyViewHolder holder, final int position) {

        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(holder.syTjImg);
        holder.syTjTitle.setText(list.get(position).getTitle());
        holder.syTjPrice.setText("￥" + Float.parseFloat(list.get(position).getPscid() + ""));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("pid", list.get(position).getPid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MySyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView syTjImg;
        private final TextView syTjTitle;
        private final TextView syTjPrice;

        public MySyViewHolder(View itemView) {
            super(itemView);

            syTjImg = (ImageView) itemView.findViewById(R.id.syTjImg);
            syTjTitle = (TextView) itemView.findViewById(R.id.syTjTitle);
            syTjPrice = (TextView) itemView.findViewById(R.id.syTjPrice);
        }
    }

}
