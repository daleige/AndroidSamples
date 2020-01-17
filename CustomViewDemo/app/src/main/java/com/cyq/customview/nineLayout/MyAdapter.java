package com.cyq.customview.nineLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cyq.customview.R;
import com.cyq.customview.nineLayout.view.NineImageAdapter;
import com.cyq.customview.nineLayout.view.NineImageLayout;

import java.util.List;
import java.util.logging.Handler;

/**
 * @author : ChenYangQi
 * date   : 2020/1/16 13:49
 * desc   :
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<List<String>> mList;
    private Context mContext;

    public MyAdapter(List<List<String>> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_nine_img_layout_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final List<String> mData = mList.get(position);
        holder.tvTitle.setText("这是" + mData.size() + "张图片的标题");
        final NineImageLayout nineImageLayout = holder.nineImageLayout;
        holder.nineImageLayout.setAdapter(new NineImageAdapter() {
            @Override
            protected int getItemCount() {
                return mData.size();
            }

            @Override
            protected View createView(LayoutInflater inflater, ViewGroup parent, int i) {
                return inflater.inflate(R.layout.item_img_layout, parent, false);
            }

            @Override
            protected void bindView(View view, final int i) {
                final ImageView imageView = view.findViewById(R.id.iv_img);
                Glide.with(mContext).load(mData.get(i)).into(imageView);
                if (mData.size() == 1) {
                    Glide.with(mContext)
                            .asBitmap()
                            .load(mData.get(0))
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                                    final int width = bitmap.getWidth();
                                    final int height = bitmap.getHeight();
                                    nineImageLayout.setSingleImage(width, height,imageView);
                                    Glide.with(mContext).load(mData.get(0)).into(imageView);
                                }
                            });
                } else {
                    Glide.with(mContext).load(mData.get(i)).into(imageView);
                }
            }

            @Override
            public void OnItemClick(int i, View view) {
                super.OnItemClick(position, view);
                Toast.makeText(mContext, "position:" + mData.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class nineImaageAdapter extends NineImageAdapter {

        @Override
        protected int getItemCount() {
            return 0;
        }

        @Override
        protected View createView(LayoutInflater inflater, ViewGroup parent, int position) {
            return null;
        }

        @Override
        protected void bindView(View view, int position) {

        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        NineImageLayout nineImageLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            nineImageLayout = itemView.findViewById(R.id.nine_image_layout);
        }
    }
}
