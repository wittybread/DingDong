package wangxuewei.bwie.com.dingdong.homeFragments.sy.search.syadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wangxuewei.bwie.com.dingdong.R;
import wangxuewei.bwie.com.dingdong.bean.GoodsListBean;
import wangxuewei.bwie.com.dingdong.homeFragments.sy.search.GoodsDetailActivity;

/**
 * Created by jim on 2017/12/16.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {

    private Context context;
    private List<GoodsListBean.DataBean> list;
    private int flag = 1;
    private View view;

    public GoodsListAdapter(Context context, List<GoodsListBean.DataBean> list, int flag) {
        this.context = context;
        this.list = list;
        this.flag = flag;
    }

    @Override
    public GoodsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (flag == 1) {
            //左图右文字
            view = LayoutInflater.from(context).inflate(R.layout.item_goodslist, parent, false);
        } else {
            //上图下文字
            view = LayoutInflater.from(context).inflate(R.layout.item_goodslist2, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GoodsListAdapter.ViewHolder holder, final int position) {
        String[] split = list.get(position).getImages().split("\\|");
//        ImageLoader.getInstance().displayImage(split[0], holder.goodsItemImg, ImageLoaderUtils.getImageOptions());
        Glide.with(context).load(split[0]).into(holder.goodsItemImg);
        holder.goodsItemTitle.setText(list.get(position).getTitle());
        holder.goodsItemPrice.setText("原价:￥" + list.get(position).getPrice());
        holder.goodsItemPrice.setPaintFlags(holder.goodsItemPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.goodsItembargainPrice.setText("折扣价:￥" + list.get(position).getBargainPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                intent.putExtra("url", list.get(position).getDetailUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView goodsItemImg;
        private final TextView goodsItemTitle;
        private final TextView goodsItemPrice;
        private final TextView goodsItembargainPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            goodsItemImg = (ImageView) itemView.findViewById(R.id.goodsItemImg);

            goodsItemTitle = (TextView) itemView.findViewById(R.id.goodsItemTitle);
            goodsItemPrice = (TextView) itemView.findViewById(R.id.goodsItemPrice);
            goodsItembargainPrice = (TextView) itemView.findViewById(R.id.goodsItembargainPrice);

        }
    }
}
