package api.impl;

import org.xutils.common.Callback;
import org.xutils.x;

import api.ApiCallBack;
import api.Config;
import api.IUser;
import api.model.Response;
import server.IServer;
import server.Server;

/**
 * Created by yangshiyou on 2016/12/15.
 */

public class UserApi implements IUser {

    IServer server = new Server();


    private String moduleName = "sys/sysuser/";

    private String mUrl = Config.getServer_url() + moduleName;

    @Override
    public void userLogin(String userName, String password, final ApiCallBack callBack) {

        server.setMethodName(moduleName + "login");

        server.setParam("phone", userName);
        server.setParam("password", password);

        server.setApiCallBack(callBack);

        server.setUseCache(true, 5);

        server.request();

    }
}
