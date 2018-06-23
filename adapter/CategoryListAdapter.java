package com.ldainteractive.nest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ldainteractive.nest.R;
import com.ldainteractive.nest.bean.BarcodeDetailBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Divyesh on 14/09/2017.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    public ViewHolder viewHolder;
    Context context;
    ArrayList<BarcodeDetailBean> barcodeDetailArrayList = null;

    public CategoryListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_category_list_raw, viewGroup, false);
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final BarcodeDetailBean barcodeDetailBean = barcodeDetailArrayList.get(position);

        if (!barcodeDetailBean.imageName.isEmpty()) {

            Glide.with(context)
                    .load(barcodeDetailBean.imageName.replace("\\", ""))
                    .placeholder(R.drawable.ic_place)
                    .into(holder.imageView);
/*
            Picasso.with(context)
                    .load(barcodeDetailBean.imageName.replace("\\", ""))
                    .placeholder(R.drawable.ic_place)
                    //.resize(350, 350)
                    .into(holder.imageView);*/
        }

        if (barcodeDetailBean.isSelected) {
            holder.ivSelected.setVisibility(View.VISIBLE);
            holder.ivChecked.setVisibility(View.VISIBLE);
            //Toast.makeText(context, "selected", Toast.LENGTH_SHORT).show();
            /*holder.ivSelected.setImageResource(R.drawable.ic_selected_bg);
            holder.ivChecked.setImageResource(R.drawable.ic_checked);*/
            //holder.ivSelected.setAlpha((float) .6);
        } else {
            holder.ivSelected.setVisibility(View.GONE);
            holder.ivChecked.setVisibility(View.GONE);
        }

        /*if (!barcodeDetailBean.isSelected) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new ImageViewer.Builder<>(context, barcodeDetailArrayList).setStartPosition(position)
                            .setFormatter(new ImageViewer.Formatter<BarcodeDetailBean>() {
                                @Override
                                public String format(BarcodeDetailBean detailBean) {
                                    return detailBean.imageName.replace("\\", "");
                                }
                            })
                            .show();
                }
            });
        }*/
    }

    @Override
    public int getItemCount() {
        return barcodeDetailArrayList.size();
    }

    public void addData(ArrayList<BarcodeDetailBean> objArrayList) {
        if (barcodeDetailArrayList == null)
            barcodeDetailArrayList = new ArrayList<BarcodeDetailBean>();
        barcodeDetailArrayList.addAll(objArrayList);

        this.notifyDataSetChanged();
    }

    public ArrayList<BarcodeDetailBean> getList() {
        return barcodeDetailArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView)
        public ImageView imageView;
        @Bind(R.id.iv_selected)
        public ImageView ivSelected;
        @Bind(R.id.img_checked)
        public ImageView ivChecked;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}