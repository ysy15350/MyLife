package model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ysy15350.mylife.BR;

/**
 * 用户登录信息
 *
 * @author yangshiyou
 */
public class UserInfo extends BaseObservable {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

}
