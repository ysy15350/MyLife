package base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import org.xutils.x;


public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends BaseActivity {

	protected T mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mPresenter = createPresenter();

		mPresenter.attachView((V) this);
	}



	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mPresenter.detachView();
	}

	protected abstract T createPresenter();

}
