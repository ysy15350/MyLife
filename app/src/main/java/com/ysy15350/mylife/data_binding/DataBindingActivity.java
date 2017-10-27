package com.ysy15350.mylife.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.databinding.ActivityDataBindingBinding;

import org.xutils.view.annotation.ViewInject;

import base.MVPBaseActivity;
import model.UserInfo;


public class DataBindingActivity extends MVPBaseActivity<DataBindingViewInterface, DataBindingPresenter> implements DataBindingViewInterface {


    @Override
    protected DataBindingPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new DataBindingPresenter(DataBindingActivity.this);


    }

    ActivityDataBindingBinding binding;

    @ViewInject(R.id.et_userName)
    private EditText et_userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data_binding);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        mPresenter.getUser("admin", "");

    }

//    @Event(value = R.id.btn_ok)
//    private void btn_okClick(View view) {
//        String name = mHolder.getViewText(R.id.et_userName);
//        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
//        binding.getUser().setName(name);
//    }


    public void btn_okClick(View view) {
        String name =et_userName.getText().toString();
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        binding.getUser().setName(name);
    }


    @Override
    public void bindUserInfo(UserInfo userInfo) {
        binding.setUser(userInfo);
        Toast.makeText(mContext, "ddd", Toast.LENGTH_SHORT).show();
    }
}
