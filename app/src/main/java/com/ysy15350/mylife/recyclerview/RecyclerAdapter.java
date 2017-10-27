package com.ysy15350.mylife.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysy15350.mylife.R;

import java.util.List;

import model.Active;

/**
 * Created by yangshiyou on 2016/11/28.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater mInflater;

    private Context mContext;

    private List<Active> mDatas;

    public RecyclerAdapter(Context context,List<Active> datas)
    {
        this.mContext=context;
        this.mDatas=datas;

        mInflater=LayoutInflater.from(mContext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mInflater.inflate(R.layout.recycler_item,parent,false);

        MyViewHolder viewHolder=new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null)
                {
                    int layoutPosition=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(v,layoutPosition);
                }
            }
        });

        Active active = mDatas.get(position);

        if (active != null) {
            holder.tv1.setText(active.getName());
        }
    }

    public void insertData(Active active,int pos)
    {
        mDatas.add(pos,active);

        notifyItemInserted(pos);
    }

    public void removeData(Active active,int pos)
    {
        //mDatas.remove(active);

        notifyItemRemoved(pos);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener)
    {
        this.mOnItemClickListener=listener;
    }

    public interface  OnItemClickListener
    {
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder
{
    TextView tv1;

    public MyViewHolder(View arg0)
    {
        super(arg0);

        tv1= (TextView) arg0.findViewById(R.id.tv_1);
    }
}
