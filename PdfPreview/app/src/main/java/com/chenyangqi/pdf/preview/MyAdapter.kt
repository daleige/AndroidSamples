package com.chenyangqi.pdf.preview

import android.content.Context
import android.graphics.pdf.PdfRenderer
import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import com.chenyangqi.pdf.preview.MyAdapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.chenyangqi.pdf.preview.R

class MyAdapter(
    private val mContext: Context,
    private val mPefRender: PdfRenderer,
    private val mBitmapArr: Array<Bitmap?>
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_pdf_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageBitmap(mBitmapArr[position])
    }

    override fun getItemCount(): Int {
        return mPefRender.pageCount
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.itemImage)
    }
}