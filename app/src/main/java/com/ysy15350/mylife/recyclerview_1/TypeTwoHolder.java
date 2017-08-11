package com.ysy15350.mylife.recyclerview_1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysy15350.mylife.R;

/**
 * Created by kirito on 2016.10.29.
 */

//<ItemTwo>泛型的具体使用
public class TypeTwoHolder extends TypeAbstarctViewHolder<ItemTwo> {
    private ImageView avater;
    private TextView name;
    private TextView content;

    public TypeTwoHolder(View itemView) {
        super(itemView);
        avater = (ImageView) itemView.findViewById(R.id.avater);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
    }

    @Override
    public void bindHolder(ItemTwo item) {
        avater.setBackgroundResource(item.avaterColor);
        name.setText(item.name);
        content.setText(item.content);
    }
}
