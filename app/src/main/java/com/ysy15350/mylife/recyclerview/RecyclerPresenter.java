package com.ysy15350.mylife.recyclerview;

import android.content.Context;

import com.ysy15350.mylife.test.TestViewInterface;

import java.util.ArrayList;
import java.util.List;

import base.BasePresenter;
import model.Active;

/**
 * Created by yangshiyou on 2016/11/27.
 */

public class RecyclerPresenter extends BasePresenter<RecyclerViewInterface> {

    public RecyclerPresenter() {
    }

    public RecyclerPresenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }


    public void getData()
    {
        List<Active> activeList=new ArrayList<Active>();

        try
        {

            for (int i='A';i<='z';i++)
            {
                Active active=new Active();
                active.setName(""+(char)i);
                activeList.add(active);
            }

            mView.bindData(activeList);

        }
        catch (Exception ex)
        {

        }
    }

}
