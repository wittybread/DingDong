package wangxuewei.bwie.com.dingdong.homeFragments.fl.fladapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.FlRightBean;
import wangxuewei.bwie.com.dingdong.utils.ImageLoaderUtils;

/**
 * Created by jim on 2017/12/14.
 */

public class FlRightDownAdapter extends RecyclerView.Adapter<FlRightDownAdapter.MyViewHolder> {

    private Context context;
    private List<FlRightBean.DataBean.ListBean> listBeen;

    public FlRightDownAdapter(Context context, List<FlRightBean.DataBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public FlRightDownAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_flrightdowm, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(FlRightDownAdapter.MyViewHolder holder, final int position) {

        ImageLoader.getInstance().displayImage(listBeen.get(position).getIcon(), holder.flRightItemDownIcon, ImageLoaderUtils.getImageOptions());
//        Glide.with(context).load(listBeen.get(position).getIcon()).into(holder.flRightItemDownIcon);
        holder.flRightItemDownName.setText(listBeen.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "position" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listBeen != null ? listBeen.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView flRightItemDownIcon;
        private final TextView flRightItemDownName;

        public MyViewHolder(View itemView) {
            super(itemView);

            flRightItemDownIcon = (ImageView) itemView.findViewById(R.id.flRightItemDownIcon);
            flRightItemDownName = (TextView) itemView.findViewById(R.id.flRightItemDownName);


        }
    }


}
