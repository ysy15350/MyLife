package api.impl;

import org.xutils.common.Callback;
import org.xutils.x;

import api.ApiCallBack;
import api.IKuaidi;
import api.model.Kuaidi;
import common.utils.JsonConvertor;
import server.IServer;
import server.Server;

/**
 * Created by yangshiyou on 2016/12/20.
 */

public class Kuaidi100Api implements IKuaidi {

    IServer server = new Server();

    JsonConvertor jsonConvertor;




    @Override
    public void query(String type, String postid, final ApiCallBack apiCallBack) {
//        String url = "http://www.kuaidi100.com/query?type=zhongtong&postid=419284983887";

        server.setUrl("http://www.kuaidi100.com/query");

        server.setParam("type", "zhongtong");
        server.setParam("postid", "419284983887");

        server.setApiCallBack(apiCallBack);

        server.request();



    }
}
