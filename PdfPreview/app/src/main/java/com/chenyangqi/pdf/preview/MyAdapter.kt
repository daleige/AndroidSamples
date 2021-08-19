package com.chenyangqi.pdf.preview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private PdfRenderer mPefRender;
    private Bitmap[] mBitmapArr;

    public MyAdapter(Context context, PdfRenderer pefRender, Bitmap[] bitmapArr) {
        this.mContext = context;
        this.mPefRender = pefRender;
        mBitmapArr = bitmapArr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pdf_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageBitmap(mBitmapArr[position]);
    }

    @Override
    public int getItemCount() {
        return mPefRender.getPageCount();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
        }
    }
}
