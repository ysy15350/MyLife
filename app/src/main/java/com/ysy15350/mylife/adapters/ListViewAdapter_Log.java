package com.ysy15350.mylife.adapters;

import android.content.Context;

import com.ysy15350.mylife.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import base.adapters.CommonAdapter;
import base.adapters.ViewHolder;

/**
 * Created by yangshiyou on 2017/7/10.
 */

public class ListViewAdapter_Log extends CommonAdapter<String> {

    public interface CancelListener {
        void cancelClick(String t);
    }

    private CancelListener mCancelListener;

    public void setCancelListener(CancelListener cancelListener) {
        this.mCancelListener = cancelListener;
    }


    public ListViewAdapter_Log(Context context, List<String> list) {
        super(context, list, R.layout.listview_item_log);
    }


    @Override
    public void convert(ViewHolder holder, final String t) {
        try {
            if (holder != null && t != null) {

                Date date = new Date();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = dateFormat.format(date);

                holder.setText(R.id.tv_log, t)
                        .setText(R.id.tv_time, dateStr);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
