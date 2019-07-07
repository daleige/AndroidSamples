package com.cyq.ninegridview.nine_grid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.cyq.ninegridview.R;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Create by 陈扬齐
 * Create on 2019-07-07
 * description:
 */
public class TypeThreeAdapter extends RecyclerView.Adapter<TypeThreeAdapter.TypeThreeViewHolder> {
    private Context mContext;
    private List<String> list;

    public TypeThreeAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public TypeThreeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_img_square, viewGroup,
                false);
        return new TypeThreeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeThreeViewHolder holder, int i) {
        Glide.with(mContext).load(list.get(i))
                .apply(bitmapTransform(new RoundedCornersTransformation(30, 0,
                        RoundedCornersTransformation.CornerType.ALL)))
                .placeholder(R.mipmap.ic_launcher).into(holder.squareImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TypeThreeViewHolder extends RecyclerView.ViewHolder {
        SquareImageView squareImageView;

        public TypeThreeViewHolder(@NonNull View itemView) {
            super(itemView);
            squareImageView = itemView.findViewById(R.id.iv_square);
        }
    }
}
