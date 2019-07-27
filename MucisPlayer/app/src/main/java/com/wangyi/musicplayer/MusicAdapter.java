package com.wangyi.musicplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.NormalTextViewHolder>{
    private final LayoutInflater mLayoutInflater;
    private String[] mTitles = null;

    public MusicAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.music);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.music_name.setText(mTitles[position]);
        holder.item_postion.setText(position+"");
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    class NormalTextViewHolder extends RecyclerView.ViewHolder {
        TextView music_name;
        TextView item_postion;
        NormalTextViewHolder(View view) {
            super(view);
            music_name = (TextView) view.findViewById(R.id.music_name);
            item_postion = view.findViewById(R.id.item_postion);

        }
    }
}
