package api;

/**
 * Created by yangshiyou on 2016/12/21.
 */

public interface IAuthApi {

    public void userLogin(String userName,String password,ApiCallBack callBack);
}
