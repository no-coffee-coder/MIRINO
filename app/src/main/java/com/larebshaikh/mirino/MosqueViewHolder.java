package com.larebshaikh.mirino;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MosqueViewHolder extends RecyclerView.ViewHolder {
    View mView;


    public MosqueViewHolder (final View itemView){
        super(itemView);
        mView=itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickListener.onItemClick(v,getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mclickListener.onItemLongClick(v,getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context ctc, String MosqueName, String MosqueArea, String Fajrtime, String Zohartime,
                           String Asrtime, String Maghribtime, String Ishatime, String Jumatime){
        TextView mosquename,mosquearea,fajrtime,zohartime,asrtime,maghribtime,ishatime,jumatime;
        mosquename = itemView.findViewById(R.id.mosquename);
        mosquearea = itemView.findViewById(R.id.mosquearea);
        zohartime =itemView.findViewById(R.id.ZoharTime);
        fajrtime=itemView.findViewById(R.id.FajrTime);
        asrtime=itemView.findViewById(R.id.AsrTime);
        maghribtime=itemView.findViewById(R.id.MaghribTime);
        ishatime=itemView.findViewById(R.id.IshaTime);
        jumatime=itemView.findViewById(R.id.jumaTime);

        mosquename.setText(MosqueName);
        mosquearea.setText(MosqueArea);
        zohartime.setText(Zohartime);
        fajrtime.setText(Fajrtime);
        asrtime.setText(Asrtime);
        maghribtime.setText(Maghribtime);
        ishatime.setText(Ishatime);
        jumatime.setText(Jumatime);

    }

    private MosqueViewHolder.ClickListener mclickListener;

    public interface ClickListener{
        void onItemClick(View v, int position);
        void onItemLongClick(View v, int position);

    }
    public void setOnClickListener(MosqueViewHolder.ClickListener clickListener){
        mclickListener=clickListener;
    }
}
