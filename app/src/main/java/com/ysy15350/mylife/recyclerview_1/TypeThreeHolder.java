package com.ysy15350.mylife.recyclerview_1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysy15350.mylife.R;

/**
 * Created by kirito on 2016.10.29.
 */

public class TypeThreeHolder extends TypeAbstarctViewHolder<ItemThree> {
    private ImageView avater;
    private TextView name;
    private TextView content;
    private ImageView iv;

    public TypeThreeHolder(View itemView) {
        super(itemView);
        avater = (ImageView) itemView.findViewById(R.id.avater);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
        iv = (ImageView) itemView.findViewById(R.id.content_color);
    }

    @Override
    public void bindHolder(ItemThree item) {
        avater.setBackgroundResource(item.avaterColor);
        name.setText(item.name);
        content.setText(item.content);
        iv.setBackgroundResource(item.contentColor);
    }
}
