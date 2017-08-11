package api;

import api.model.Response;

/**
 * Created by yangshiyou on 2016/12/15.
 */

public interface IUser {
    public void userLogin(String userName,String password,ApiCallBack callBack);
}
