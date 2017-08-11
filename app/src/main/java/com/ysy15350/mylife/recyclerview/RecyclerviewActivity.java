package com.ysy15350.mylife.recyclerview;

/**
 * Created by yangshiyou on 2016/11/27.
 */

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.test.TestPresenter;
import com.ysy15350.mylife.test.TestViewInterface;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import base.MVPBaseActivity;
import model.Active;

/**
 * 5.0之后，之前有支持库
 */
@ContentView(R.layout.activity_recyclerview)
public class RecyclerviewActivity extends MVPBaseActivity<RecyclerViewInterface, RecyclerPresenter> implements RecyclerViewInterface {

    /**
     * Adapter
     * ViewHolder
     * LayoutManager
     * ItemDecoration
     * ItemAnimator
     */

    /**
     *
     */

    private RecyclerView recyclerview;

    private RecyclerAdapter mAdapter;


    @Override
    protected RecyclerPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new RecyclerPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);

        mPresenter.getData();
    }

    @Override
    public void bindData(List<Active> activeList) {

        mAdapter = new RecyclerAdapter(this, activeList);

        mAdapter.setmOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tv1 = (TextView) view.findViewById(R.id.tv_1);
                Toast.makeText(RecyclerviewActivity.this, tv1.getText().toString() + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


        recyclerview.setAdapter(mAdapter);

        //ListView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //GridView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);//3列

        //水平GridView
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        recyclerview.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);

        dividerItemDecoration.setDivider(R.drawable.divider_gradient_1);//渐变分割线样式

        //recyclerview.addItemDecoration(dividerItemDecoration);

        recyclerview.setItemAnimator(new DefaultItemAnimator());

    }


    @Event(value = R.id.btn_add)
    private void addClick(View view) {

        Active active = new Active();
        active.setName("insert");

        mAdapter.insertData(active, 1);
    }


    @Event(value = R.id.btn_remove)
    private void removeClick(View view) {

        Active active = new Active();
        active.setName("insert");

        mAdapter.removeData(active, 1);

    }


}
